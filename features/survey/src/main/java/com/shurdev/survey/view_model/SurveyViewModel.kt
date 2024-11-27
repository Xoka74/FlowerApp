package com.shurdev.survey.view_model

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.models.survey.Question
import com.shurdev.domain.models.survey.AnsweredQuestion
import com.shurdev.domain.repositories.SurveyRepository
import com.shurdev.survey.utils.SurveyActionListener
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SurveyViewModel @Inject constructor(
    private val surveyRepository: SurveyRepository,
) : BaseViewModel<SurveyUiState>(SurveyLoadingUiState), SurveyActionListener {

    init {
        updateUiState { SurveyLoadingUiState }

        viewModelScope.launch {
            runSuspendCatching {
                val answeredQuestions = surveyRepository.getResultsFromDatabase()

                val questions: List<Question>
                val answersIndices: List<Int>

                if (answeredQuestions.isEmpty()) {
                    questions = surveyRepository.getQuestions()
                    answersIndices = List(questions.size) { 0 }
                } else {
                    questions = answeredQuestions.map { it.question }

                    answersIndices = answeredQuestions.map { result ->
                        val answer = result.answer
                        val question = questions.first { it.id == answer.questionId }

                        val answerIndex = question.answerOptions.indexOfFirst { answerOption ->
                            answerOption == answer.answer
                        }

                        return@map answerIndex
                    }
                }


                updateUiState {
                    SurveyLoadedUiState(
                        questions = questions,
                        answersIndices = answersIndices,
                        currentQuestionIndex = 0
                    )
                }
            }.onFailure {
                updateUiState { SurveyErrorUiState }
            }
        }
    }

    override fun onFinishSurvey() {
        if (uiState.value !is SurveyLoadedUiState) {
            return
        }

        val loadedState = uiState.value as SurveyLoadedUiState
        val questions = loadedState.questions
        val answersIndices = loadedState.answersIndices

        viewModelScope.launch {

            val answers = getAnswers(answersIndices, questions)
            val results = getResults(answers, questions)

            surveyRepository.submitAnswers(answers = answers)
            surveyRepository.saveResultsToDatabase(results)
        }
    }

    private fun getResults(
        answers: List<Answer>,
        questions: List<Question>
    ): List<AnsweredQuestion> {

        return questions.mapIndexed { index, question ->
            val answer = answers[index]

            AnsweredQuestion(
                question = question,
                answer = answer
            )
        }
    }

    private fun getAnswers(
        answersIndices: List<Int>,
        questions: List<Question>
    ): List<Answer> {

        return answersIndices.mapIndexed { index, selectedAnswerIndex ->
            val question = questions[index]

            Answer(
                answer = question.answerOptions[selectedAnswerIndex],
                questionId = question.id
            )
        }
    }

    override fun onAnswerClick(answerIndex: Int) {
        if (uiState.value !is SurveyLoadedUiState) {
            return
        }

        updateUiState {
            val loadedState = uiState.value as SurveyLoadedUiState

            val currentQuestion = loadedState.currentQuestionIndex
            val answersIndices = loadedState.answersIndices.toMutableList()

            answersIndices[currentQuestion] = answerIndex

            return@updateUiState loadedState.copy(
                answers = answers
            )
        }
    }

    override fun onSwipe(targetPage: Int) {
        if (uiState.value !is SurveyLoadedUiState) {
            return
        }

        updateUiState {
            val loadedState = uiState.value as SurveyLoadedUiState

            if (targetPage >= loadedState.questions.size) {
                return@updateUiState loadedState
            } else {
                return@updateUiState loadedState.copy(
                    currentQuestion = targetPage
                )
            }
        }
    }

    override fun onBackClick() {
        if (uiState.value !is SurveyLoadedUiState) {
            return
        }

        updateUiState {
            val loadedState = uiState.value as SurveyLoadedUiState
            val currentQuestion = loadedState.currentQuestion

            if (currentQuestion == 0) {
                return@updateUiState loadedState
            } else {
                return@updateUiState loadedState.copy(
                    currentQuestion = currentQuestion - 1
                )
            }
        }
    }

    override fun onSkipClick() {
        if (uiState.value !is SurveyLoadedUiState) {
            return
        }

        updateUiState {
            val loadedState = uiState.value as SurveyLoadedUiState

            return@updateUiState loadedState.copy(
                isFinished = true
            )
        }
    }

    override fun onNextClick() {
        if (uiState.value !is SurveyLoadedUiState) {
            return
        }

        updateUiState {
            val loadedState = uiState.value as SurveyLoadedUiState
            val currentQuestion = loadedState.currentQuestion

            if (currentQuestion == loadedState.questions.size - 1) {
                return@updateUiState loadedState.copy(
                    isFinished = true
                )
            } else {
                return@updateUiState loadedState.copy(
                    currentQuestion = currentQuestion + 1
                )
            }
        }
    }
}
