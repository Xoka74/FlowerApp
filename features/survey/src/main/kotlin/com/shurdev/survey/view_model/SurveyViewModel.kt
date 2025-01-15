package com.shurdev.survey.view_model

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.models.survey.Question
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
        loadQuestions()
    }

    fun loadQuestions() {
        updateUiState { SurveyLoadingUiState }

        viewModelScope.launch {
            runSuspendCatching {
                val questions = surveyRepository.getQuestions()
                val answersIndices = List(questions.size) { 0 }

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
        viewModelScope.launch {
            val loadedState = (uiState.value as? SurveyLoadedUiState) ?: return@launch
            val questions = loadedState.questions
            val answersIndices = loadedState.answersIndices
            val answers = getAnswers(answersIndices, questions)

            runSuspendCatching {
                surveyRepository.submitAnswers(answers = answers)
            }.onFailure {
                // TODO handle error
                print("error")
            }
        }
    }

    private fun getAnswers(
        answersIndices: List<Int>,
        questions: List<Question>,
    ): List<Answer> {

        return answersIndices.mapIndexed { index, selectedAnswerIndex ->
            val question = questions[index]

            Answer(
                answer = question.answerOptions[selectedAnswerIndex],
                questionId = question.id,
                options = question.answerOptions,
            )
        }
    }

    override fun onAnswerClick(answerIndex: Int) {
        transformUiState<SurveyLoadedUiState, SurveyLoadedUiState> { state ->
            val currentQuestion = state.currentQuestionIndex
            val answersIndices = state.answersIndices.toMutableList()

            answersIndices[currentQuestion] = answerIndex

            state.copy(
                answersIndices = answersIndices
            )
        }
    }

    override fun onSwipe(targetPage: Int) {
        transformUiState<SurveyLoadedUiState, SurveyLoadedUiState> { state ->
            if (targetPage >= state.questions.size) {
                state
            } else {
                state.copy(
                    currentQuestionIndex = targetPage
                )
            }
        }
    }

    override fun onBackClick() {
        transformUiState<SurveyLoadedUiState, SurveyLoadedUiState> { state ->
            val currentQuestion = state.currentQuestionIndex

            if (currentQuestion == 0) {
                state
            } else {
                state.copy(
                    currentQuestionIndex = currentQuestion - 1
                )
            }
        }
    }

    override fun onSkipClick() {
        transformUiState<SurveyLoadedUiState, SurveyLoadedUiState> { state ->
            state.copy(
                isSkipped = true
            )
        }
    }

    override fun onNextClick() {
        transformUiState<SurveyLoadedUiState, SurveyLoadedUiState> { state ->
            val currentQuestion = state.currentQuestionIndex

            if (currentQuestion == state.questions.size - 1) {
                state.copy(
                    isFinished = true
                )
            } else {
                state.copy(
                    currentQuestionIndex = currentQuestion + 1
                )
            }
        }
    }
}
