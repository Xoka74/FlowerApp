package com.shurdev.survey.view_model

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.survey.Answer
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
                val questions = surveyRepository.getQuestions()

                updateUiState {
                    SurveyLoadedUiState(
                        questions = questions,
                        answers = List(questions.size) { 0 },
                        currentQuestion = 0
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

        viewModelScope.launch {
            surveyRepository.submitAnswers(
                answers = loadedState.answers.mapIndexed { index, answer ->
                    Answer(
                        answer = questions[index].answerOptions[answer],
                        questionId = questions[index].id
                    )
                }
            )
        }
    }

    override fun onAnswerClick(answerId: Int) {
        if (uiState.value !is SurveyLoadedUiState) {
            return
        }

        updateUiState {
            val loadedState = uiState.value as SurveyLoadedUiState

            val currentQuestion = loadedState.currentQuestion
            val answers = loadedState.answers.toMutableList()

            answers[currentQuestion] = answerId

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
