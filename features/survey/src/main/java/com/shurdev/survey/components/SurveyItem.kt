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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shurdev.survey.model.SurveyData
import com.shurdev.survey.model.QuestionWithSingleAnswer

@Composable
fun SurveyItem(
    data: SurveyData
) {
    println("OnboardingItem: ${data.id}")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // TODO add image
        /*AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp)),
            model = ImageRequest.Builder(LocalContext.current)
//                .data(flower.imageLink)
//                .placeholder(R.drawable.flower_placeholder_1)
                .build(),
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )*/

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = data.content.question,
            style = MaterialTheme.typography.headlineMedium,
            // fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 1.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))

        when (data.content) {
            is QuestionWithSingleAnswer -> {
                val radioOptions = data.content.answerOptions
                var selectedOption by remember { mutableStateOf(radioOptions[0]) }

                val isItemSelected: (String) -> Boolean = { selectedOption == it }
                val selectItem: (String) -> Unit = { selectedOption = it }

                radioOptions.forEach { answerOption ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .selectable(
                                    selected = isItemSelected(answerOption),
                                    onClick = { selectItem(answerOption) },
                                    role = Role.RadioButton
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(
                                selected = isItemSelected(answerOption),
                                onClick = { selectItem(answerOption) }
                            )

                            Text(
                                text = answerOption
                            )
                        }
                    }

                }
            }
        }
    }
}