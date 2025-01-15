package com.shurdev.survey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.survey.components.BottomSection
import com.shurdev.survey.components.SurveyItem
import com.shurdev.survey.components.TopSection
import com.shurdev.survey.mocks.QUESTIONS
import com.shurdev.survey.model.SurveyData
import com.shurdev.survey.utils.SurveyActionListener
import com.shurdev.survey.view_model.SurveyErrorUiState
import com.shurdev.survey.view_model.SurveyLoadedUiState
import com.shurdev.survey.view_model.SurveyLoadingUiState
import com.shurdev.survey.view_model.SurveyUiState
import com.shurdev.survey.view_model.SurveyViewModel
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.buttons.PrimaryButton
import com.shurdev.ui_kit.buttons.SecondaryButton
import com.shurdev.ui_kit.errors.ErrorView
import com.shurdev.ui_kit.layouts.Center
import com.shurdev.ui_kit.loaders.Loader
import kotlinx.coroutines.launch

@Composable
internal fun SurveyRoute(
    onFinishSurvey: () -> Unit,
) {
    val viewModel = hiltViewModel<SurveyViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    SurveyScreen(
        uiState = uiState,
        actionListener = viewModel,
        onFinishSurvey = {
            viewModel.onFinishSurvey()
            onFinishSurvey()
        },
        onSkipSurvey = onFinishSurvey,
        onTryAgain = viewModel::loadQuestions,
    )
}

@Composable
internal fun SurveyScreen(
    uiState: SurveyUiState,
    actionListener: SurveyActionListener,
    onFinishSurvey: () -> Unit,
    onSkipSurvey: () -> Unit,
    onTryAgain: () -> Unit,
) {
    LaunchedEffect(uiState) {
        if (uiState !is SurveyLoadedUiState) return@LaunchedEffect

        if (uiState.isSkipped) {
            onSkipSurvey()
        } else if (uiState.isFinished) {
            onFinishSurvey()
        }
    }

    when (uiState) {
        is SurveyLoadingUiState -> Loader()
        is SurveyErrorUiState -> Center {
            ErrorView()

            Spacer(Modifier.height(12.dp))

            PrimaryButton(
                text = stringResource(R.string.try_again),
                onClick = onTryAgain,
            )

            Spacer(Modifier.height(12.dp))

            SecondaryButton(
                text = stringResource(R.string.go_back),
                onClick = onSkipSurvey,
            )
        }

        is SurveyLoadedUiState -> {
            val scope = rememberCoroutineScope()
            val questions = uiState.questions
            val pagerState = rememberPagerState { questions.size }

            val currentQuestionIndex = uiState.currentQuestionIndex

            if (currentQuestionIndex != pagerState.currentPage) {
                actionListener.onSwipe(pagerState.currentPage)
            }

            val isQuestionFirst = currentQuestionIndex == 0
            val isQuestionLast = currentQuestionIndex == questions.size - 1

            LaunchedEffect(currentQuestionIndex) {
                scope.launch {
                    pagerState.animateScrollToPage(currentQuestionIndex)
                }
            }

            Column(modifier = Modifier.fillMaxSize()) {

                TopSection(
                    onBackClick = actionListener::onBackClick,
                    onSkipClick = actionListener::onSkipClick,
                    showBackButton = !isQuestionFirst
                )

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxHeight(0.9f)
                        .fillMaxWidth()
                ) { page ->
                    SurveyItem(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),
                        data = SurveyData(
                            id = page,
                            content = questions[page],
                        ),
                        onAnswerClick = actionListener::onAnswerClick,
                        selectedOption = uiState.answersIndices[page]
                    )
                }

                BottomSection(
                    onNextClick = actionListener::onNextClick,
                    isQuestionLast = isQuestionLast
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun SurveyPreview() {
    SurveyScreen(
        uiState = SurveyLoadedUiState(
            questions = QUESTIONS,
            answersIndices = (1..QUESTIONS.size).toList(),
            currentQuestionIndex = 0
        ),
        onFinishSurvey = {},
        onSkipSurvey = {},
        onTryAgain = {},
        actionListener = object : SurveyActionListener {
            override fun onNextClick() {}
            override fun onBackClick() {}
            override fun onSkipClick() {}
            override fun onAnswerClick(answerIndex: Int) {}
            override fun onSwipe(targetPage: Int) {}
            override fun onFinishSurvey() {}
        },
    )
}
