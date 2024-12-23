//package com.shurdev.ui_kit.buttons
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TimePicker
//import androidx.compose.material3.rememberTimePickerState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun TimePickerButton(
//    modifier: Modifier = Modifier,
//    title: String,
//    onDateChanged: (Long) -> Unit,
//) {
//    val typography = MaterialTheme.typography
//
//    var showModal by remember { mutableStateOf(false) }
//
//    val interactionSource = remember { MutableInteractionSource() }
//
//    Box(
//        modifier = Modifier.clickable(
//            interactionSource = interactionSource,
//            indication = null
//        ) {
//            showModal = true
//        },
//    ) {
//        Row(
//            modifier = modifier,
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            Text(
//                text = title,
//                style = typography.titleSmall
//            )
//
//            Spacer(Modifier.weight(1f))
//
////            if (selectedItem != null) {
////                Text(stringResource(itemToResId(selectedItem)))
////            }
//
//            Text("порно")
//
//            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
//        }
//    }
//
//    if (showModal) {
//        TimePickerModal(
//            onDateSelected = onDateChanged,
//            onDismiss = { showModal = false }
//        )
//    }
//
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TimePickerModal(
//    onDateSelected: (Long) -> Unit,
//    onDismiss: () -> Unit,
//) {
//    val datePickerState = rememberTimePickerState()
//
//    TimePicker(
////        onDismissRequest = onDismiss,
////        confirmButton = {
////            TextButton(onClick = {
////                val value = datePickerState.selectedDateMillis
////                if (value != null) {
////                    onDateSelected(value)
////                }
////
////                onDismiss()
////            }) {
////                Text("OK")
////            }
////        },
////        dismissButton = {
////            TextButton(onClick = onDismiss) {
////                Text("Cancel")
////            }
////        }
//    ) {
//        TimePicker(state = datePickerState)
//    }
//}
//
//@Preview
//@Composable
//fun TimePickerPreview() {
//    Surface {
//        TimePickerButton(
//            modifier = Modifier
//                .padding(8.dp)
//                .fillMaxWidth(),
//            title = "asdasdasd",
//            onDateChanged = {}
//        )
//    }
//}
