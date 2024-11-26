package com.shurdev.survey.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.models.survey.Question
import com.shurdev.domain.models.survey.AnsweredQuestion
import com.shurdev.domain.repositories.SurveyRepository
import com.shurdev.survey.utils.SurveyActionListener
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SurveyViewModel @Inject constructor(
    private val surveyRepository: SurveyRepository,
) : ViewModel(), SurveyActionListener {

    private var _uiState: MutableStateFlow<SurveyUiState> = MutableStateFlow(SurveyLoadingUiState)
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = SurveyLoadingUiState

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


                _uiState.update {
                    SurveyLoadedUiState(
                        questions = questions,
                        answersIndices = answersIndices,
                        currentQuestionIndex = 0
                    )
                }
            }.onFailure {
                _uiState.update { SurveyErrorUiState }
            }
        }
    }

    override fun onFinishSurvey() {
        if (_uiState.value !is SurveyLoadedUiState) {
            return
        }

        val loadedState = _uiState.value as SurveyLoadedUiState

        val questions = loadedState.questions
        val answersIndices = loadedState.answersIndices

        viewModelScope.launch {

            val answers = getAnswers(answersIndices, questions)
            val results = getResults(answers, questions)

            runSuspendCatching {
                surveyRepository.submitAnswers(answers = answers)
            }.onFailure {
                println("Error: $it")
                // TODO handle error
            }

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
        if (_uiState.value !is SurveyLoadedUiState) {
            return
        }

        _uiState.update {
            val loadedState = _uiState.value as SurveyLoadedUiState

            val currentQuestion = loadedState.currentQuestionIndex
            val answersIndices = loadedState.answersIndices.toMutableList()

            answersIndices[currentQuestion] = answerIndex

            return@update loadedState.copy(
                answersIndices = answersIndices
            )
        }
    }

    override fun onSwipe(targetPage: Int) {
        if (_uiState.value !is SurveyLoadedUiState) {
            return
        }

        _uiState.update {
            val loadedState = _uiState.value as SurveyLoadedUiState

            if (targetPage >= loadedState.questions.size) {
                return@update loadedState
            } else {
                return@update loadedState.copy(
                    currentQuestionIndex = targetPage
                )
            }
        }
    }

    override fun onBackClick() {
        if (_uiState.value !is SurveyLoadedUiState) {
            return
        }

        _uiState.update {
            val loadedState = _uiState.value as SurveyLoadedUiState
            val currentQuestionIndex = loadedState.currentQuestionIndex

            if (currentQuestionIndex == 0) {
                return@update loadedState
            } else {
                return@update loadedState.copy(
                    currentQuestionIndex = currentQuestionIndex - 1
                )
            }
        }
    }

    override fun onSkipClick() {
        if (_uiState.value !is SurveyLoadedUiState) {
            return
        }

        _uiState.update {
            val loadedState = _uiState.value as SurveyLoadedUiState

            return@update loadedState.copy(
                isFinished = true
            )
        }
    }

    override fun onNextClick() {
        if (_uiState.value !is SurveyLoadedUiState) {
            return
        }

        _uiState.update {
            val loadedState = _uiState.value as SurveyLoadedUiState
            val currentQuestionIndex = loadedState.currentQuestionIndex

            if (currentQuestionIndex == loadedState.questions.size - 1) {
                return@update loadedState.copy(
                    isFinished = true
                )
            } else {
                return@update loadedState.copy(
                    currentQuestionIndex = currentQuestionIndex + 1
                )
            }
        }
    }
}
