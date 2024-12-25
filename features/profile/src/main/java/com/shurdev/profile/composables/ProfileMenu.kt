package com.shurdev.profile.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.ui_kit.R

@Composable
fun ProfileMenu(
    onLogoutClick: () -> Unit = {},
    onTakeSurveyClick: () -> Unit = {},
    onRecommendedPlantsClick: () -> Unit,
    onTradeClick: () -> Unit = {}
) {
    Column {
        val takeSurveyString = stringResource(R.string.take_survey)
        val recommendedFlowersString =
            stringResource(com.shurdev.profile.R.string.recommended_plants)
        val tradeString = stringResource(com.shurdev.profile.R.string.plants_trade);
        val logoutString = stringResource(R.string.logout)

        ProfileOption(
            text = takeSurveyString,
            onClick = onTakeSurveyClick,
        )

        ProfileOption(
            text = recommendedFlowersString,
            onClick = onRecommendedPlantsClick
        )

        ProfileOption(
            text = tradeString,
            onClick = onTradeClick
        )

        HorizontalDivider()

        ProfileAction(
            text = logoutString,
            onClick = onLogoutClick,
        )
    }
}