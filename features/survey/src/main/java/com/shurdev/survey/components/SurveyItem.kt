package com.shurdev.survey.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shurdev.survey.mocks.QUESTIONS
import com.shurdev.survey.model.SurveyData

@Composable
internal fun SurveyItem(
    data: SurveyData,
    onAnswerClick: (Int) -> Unit,
    selectedOption: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // TODO add image

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = data.content.question,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 1.sp,
        )

        Spacer(modifier = Modifier.height(8.dp))


        val radioOptions = data.content.answerOptions

        val isItemSelected: (Int) -> Boolean = { selectedOption == it }

        radioOptions.forEachIndexed { index, answerOption ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = isItemSelected(index),
                            onClick = { onAnswerClick(index) },
                            role = Role.RadioButton
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = isItemSelected(index),
                        onClick = { onAnswerClick(index) }
                    )

                    Text(
                        text = answerOption
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun SurveyItemPreview() {
    SurveyItem(
        data = SurveyData(
            id = 1,
            imageSrc = "",
            content = QUESTIONS[0]
        ),
        onAnswerClick = {},
        selectedOption = 1
    )
}