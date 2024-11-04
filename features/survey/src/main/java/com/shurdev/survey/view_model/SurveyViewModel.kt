package com.shurdev.survey.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.repositories.SurveyRepository
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
) : ViewModel() {

    private var _uiState: MutableStateFlow<SurveyUiState> = MutableStateFlow(SurveyLoadingUiState)
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = SurveyLoadingUiState

        viewModelScope.launch {
            runSuspendCatching {
                val questions = surveyRepository.getQuestions()

                _uiState.update {
                    SurveyLoadedUiState(
                        questions = questions,
                        answers = List(questions.size) { 0 },
                        currentQuestion = 0
                    )
                }
            }.onFailure {
                _uiState.update { SurveyErrorUiState }
            }
        }
    }

    fun onFinishSurvey() {
        if (_uiState.value !is SurveyLoadedUiState) {
            return
        }

        val loadedState = _uiState.value as SurveyLoadedUiState
        val questions = loadedState.questions

        viewModelScope.launch {
            surveyRepository.submitAnswers(
                answers = loadedState.answers.mapIndexed { index, answer ->
                    Answer(
                        answer = questions[index].answerOptions[answer],
                        questionId = questions[index].id
                    )
                }.also {
                    println(it)
                }
            )
        }
    }

    fun onAnswerClick(answer: Int) {
        if (_uiState.value !is SurveyLoadedUiState) {
            return
        }

        _uiState.update {
            val loadedState = _uiState.value as SurveyLoadedUiState

            val currentQuestion = loadedState.currentQuestion
            val answers = loadedState.answers.toMutableList()

            answers[currentQuestion] = answer

            return@update loadedState.copy(
                answers = answers
            )
        }
    }

    fun onSwipe(targetPage: Int) {
        if (_uiState.value !is SurveyLoadedUiState) {
            return
        }

        _uiState.update {
            val loadedState = _uiState.value as SurveyLoadedUiState

            if (targetPage >= loadedState.questions.size) {
                return@update loadedState
            } else {
                return@update loadedState.copy(
                    currentQuestion = targetPage
                )
            }
        }
    }

    fun onBackClick() {
        if (_uiState.value !is SurveyLoadedUiState) {
            return
        }

        _uiState.update {
            val loadedState = _uiState.value as SurveyLoadedUiState
            val currentQuestion = loadedState.currentQuestion

            if (currentQuestion == 0) {
                return@update loadedState
            } else {
                return@update loadedState.copy(
                    currentQuestion = currentQuestion - 1
                )
            }
        }
    }

    fun onSkipClick() {
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

    fun onNextClick() {
        if (_uiState.value !is SurveyLoadedUiState) {
            return
        }

        _uiState.update {
            val loadedState = _uiState.value as SurveyLoadedUiState
            val currentQuestion = loadedState.currentQuestion

            if (currentQuestion == loadedState.questions.size - 1) {
                return@update loadedState.copy(
                    isFinished = true
                )
            } else {
                return@update loadedState.copy(
                    currentQuestion = currentQuestion + 1
                )
            }
        }
    }
}