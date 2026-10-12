package com.example.unitask.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp

@Composable
fun GridBackgroundLines() {
    val lineColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)

    Canvas(modifier = Modifier.fillMaxSize()) {
        val step = 36.dp.toPx()
        var x = step
        while (x < size.width) {
            drawLine(
                color = lineColor,
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = 1f
            )
            x += step
        }
    }
}
