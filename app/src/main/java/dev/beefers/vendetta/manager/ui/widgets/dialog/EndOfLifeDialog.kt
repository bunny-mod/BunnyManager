package dev.beefers.vendetta.manager.ui.widgets.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

@Composable
fun EndOfLifeDialog(onDismiss: () -> Unit) {
    var secondsLeft by remember { mutableIntStateOf(5) }
    val canDismiss = secondsLeft <= 0

    LaunchedEffect(secondsLeft) {
        if (secondsLeft > 0) {
            delay(1000L)
            secondsLeft--
        }
    }

    val maybeOnDismiss = { if (canDismiss) onDismiss() }

    AlertDialog(
        onDismissRequest = maybeOnDismiss,
        title = { Text(text = "Project Unmaintained") },
        text = {
            Text(
                text = "Bunny is no longer maintained. Versions have been set to the latest ones supported by Bunny. Please consider switching to an alternative client mod."
            )
        },
        icon = {
            Icon(Icons.Filled.Warning, contentDescription = null)
        },
        confirmButton = {
            FilledTonalButton(
                enabled = canDismiss,
                onClick = maybeOnDismiss,
            ) {
                Text(text = "Understood${if (!canDismiss) " ($secondsLeft)" else ""}")
            }
        }
    )
}
