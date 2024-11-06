package com.shurdev.survey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.survey.components.BottomSection
import com.shurdev.survey.components.SurveyItem
import com.shurdev.survey.components.TopSection
import com.shurdev.survey.mocks.QUESTIONS
import com.shurdev.survey.model.SurveyData
import com.shurdev.survey.view_model.SurveyErrorUiState
import com.shurdev.survey.view_model.SurveyLoadedUiState
import com.shurdev.survey.view_model.SurveyLoadingUiState
import com.shurdev.survey.view_model.SurveyUiState
import com.shurdev.survey.view_model.SurveyViewModel
import kotlinx.coroutines.launch

@Composable
internal fun SurveyRoute(
    onFinishSurvey: () -> Unit
) {
    val viewModel = hiltViewModel<SurveyViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    SurveyScreen(
        uiState = uiState,
        onAnswerClick = viewModel::onAnswerClick,
        onBackClick = viewModel::onBackClick,
        onSkipClick = viewModel::onSkipClick,
        onNextClick = viewModel::onNextClick,
        onFinishSurvey = {
            viewModel.onFinishSurvey()
            onFinishSurvey()
        },
        onSwipe = viewModel::onSwipe
    )
}

@Composable
internal fun SurveyScreen(
    uiState: SurveyUiState,
    onAnswerClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    onSwipe: (page: Int) -> Unit,
    onFinishSurvey: () -> Unit
) {
    when (uiState) {
        is SurveyLoadedUiState -> {

            if (uiState.isFinished) {
                onFinishSurvey()
                return
            }

            val scope = rememberCoroutineScope()
            val questions = uiState.questions
            val pagerState = rememberPagerState { questions.size }

            val currentQuestion = uiState.currentQuestion

            if (currentQuestion != pagerState.currentPage) {
                onSwipe(pagerState.currentPage)
            }

            val isQuestionFirst = currentQuestion == 0
            val isQuestionLast = currentQuestion == questions.size - 1

            LaunchedEffect(currentQuestion) {
                scope.launch {
                    pagerState.animateScrollToPage(currentQuestion)
                }
            }

            Column(modifier = Modifier.fillMaxSize()) {

                TopSection(
                    onBackClick = onBackClick,
                    onSkipClick = onSkipClick,
                    showBackButton = !isQuestionFirst
                )

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxHeight(0.9f)
                        .fillMaxWidth()
                ) { page ->
                    SurveyItem(
                        data = SurveyData(
                            id = page,
                            imageSrc = "",
                            content = questions[page],
                        ),
                        onAnswerClick = onAnswerClick,
                        selectedOption = uiState.answers[page]
                    )
                }

                BottomSection(
                    onNextClick = onNextClick,
                    isQuestionLast = isQuestionLast
                )
            }
        }

        is SurveyLoadingUiState -> {}
        is SurveyErrorUiState -> {}
    }
}

@Preview(showBackground = true)
@Composable
internal fun SurveyPreview() {
    SurveyScreen(
        uiState = SurveyLoadedUiState(
            questions = QUESTIONS,
            answers = (1..QUESTIONS.size).toList(),
            currentQuestion = 0
        ),
        onAnswerClick = {},
        onNextClick = {},
        onSkipClick = {},
        onBackClick = {},
        onFinishSurvey = {},
        onSwipe = {}
    )
}
