package com.example.unitask.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun WorkspaceIllustration(modifier: Modifier = Modifier) {
    val primaryDark = MaterialTheme.colorScheme.onBackground
    val lineDark = MaterialTheme.colorScheme.onBackground
    val screenBlue = MaterialTheme.colorScheme.primary
    val lightBlue = MaterialTheme.colorScheme.secondary
    val paleBlue = MaterialTheme.colorScheme.primaryContainer
    val plantGreen = MaterialTheme.colorScheme.primary

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        drawLine(
            color = lineDark,
            start = Offset(w * 0.05f, h * 0.88f),
            end = Offset(w * 0.95f, h * 0.88f),
            strokeWidth = 2f
        )

        val monitorLeft = w * 0.28f
        val monitorTop = h * 0.18f
        val monitorWidth = w * 0.45f
        val monitorHeight = h * 0.52f

        drawLine(
            color = primaryDark,
            start = Offset(w * 0.5f, monitorTop + monitorHeight),
            end = Offset(w * 0.5f, h * 0.88f),
            strokeWidth = 4f
        )
        drawLine(
            color = primaryDark,
            start = Offset(w * 0.42f, h * 0.88f),
            end = Offset(w * 0.58f, h * 0.88f),
            strokeWidth = 3f
        )

        drawRoundRect(
            color = primaryDark,
            topLeft = Offset(monitorLeft, monitorTop),
            size = Size(monitorWidth, monitorHeight),
            cornerRadius = CornerRadius(6.dp.toPx()),
            style = Stroke(width = 2.5f)
        )

        drawRoundRect(
            color = Color.White,
            topLeft = Offset(monitorLeft + 2f, monitorTop + 2f),
            size = Size(monitorWidth - 4f, monitorHeight - 4f),
            cornerRadius = CornerRadius(4.dp.toPx())
        )

        val screenContentLeft = monitorLeft + 6.dp.toPx()
        val screenContentTop = monitorTop + 6.dp.toPx()
        val screenContentW = monitorWidth - 12.dp.toPx()

        drawRoundRect(
            color = screenBlue,
            topLeft = Offset(screenContentLeft, screenContentTop),
            size = Size(screenContentW, 10.dp.toPx()),
            cornerRadius = CornerRadius(2.dp.toPx())
        )

        drawCircle(
            color = primaryDark,
            radius = 5.dp.toPx(),
            center = Offset(screenContentLeft + screenContentW * 0.75f, screenContentTop + 20.dp.toPx()),
            style = Stroke(width = 1.5f)
        )

        drawRoundRect(
            color = lightBlue,
            topLeft = Offset(screenContentLeft + 4.dp.toPx(), screenContentTop + 14.dp.toPx()),
            size = Size(screenContentW * 0.45f, 4.dp.toPx()),
            cornerRadius = CornerRadius(2.dp.toPx())
        )
        drawRoundRect(
            color = paleBlue,
            topLeft = Offset(screenContentLeft + 4.dp.toPx(), screenContentTop + 22.dp.toPx()),
            size = Size(screenContentW * 0.55f, 3.dp.toPx()),
            cornerRadius = CornerRadius(2.dp.toPx())
        )
        drawRoundRect(
            color = paleBlue,
            topLeft = Offset(screenContentLeft + 4.dp.toPx(), screenContentTop + 28.dp.toPx()),
            size = Size(screenContentW * 0.35f, 3.dp.toPx()),
            cornerRadius = CornerRadius(2.dp.toPx())
        )

        val laptopLeft = w * 0.1f
        val laptopTop = h * 0.48f
        val laptopW = w * 0.22f
        val laptopH = h * 0.32f
        drawRoundRect(
            color = primaryDark,
            topLeft = Offset(laptopLeft, laptopTop),
            size = Size(laptopW, laptopH),
            cornerRadius = CornerRadius(4.dp.toPx()),
            style = Stroke(width = 2f)
        )
        drawLine(
            color = lightBlue,
            start = Offset(laptopLeft + 4.dp.toPx(), laptopTop + 8.dp.toPx()),
            end = Offset(laptopLeft + laptopW - 8.dp.toPx(), laptopTop + 8.dp.toPx()),
            strokeWidth = 2f
        )
        drawLine(
            color = paleBlue,
            start = Offset(laptopLeft + 4.dp.toPx(), laptopTop + 14.dp.toPx()),
            end = Offset(laptopLeft + laptopW - 12.dp.toPx(), laptopTop + 14.dp.toPx()),
            strokeWidth = 2f
        )

        val lampBaseX = w * 0.82f
        val lampPath = Path().apply {
            moveTo(lampBaseX, h * 0.88f)
            lineTo(lampBaseX + 6.dp.toPx(), h * 0.35f)
            lineTo(w * 0.72f, h * 0.18f)
        }
        drawPath(
            path = lampPath,
            color = primaryDark,
            style = Stroke(width = 2.5f)
        )
        val shadePath = Path().apply {
            moveTo(w * 0.7f, h * 0.14f)
            lineTo(w * 0.77f, h * 0.22f)
            lineTo(w * 0.67f, h * 0.25f)
            close()
        }
        drawPath(path = shadePath, color = primaryDark)

        val potLeft = w * 0.84f
        val potTop = h * 0.68f
        val potW = w * 0.1f
        val potH = h * 0.2f
        drawRoundRect(
            color = primaryDark,
            topLeft = Offset(potLeft, potTop),
            size = Size(potW, potH),
            cornerRadius = CornerRadius(2.dp.toPx()),
            style = Stroke(width = 2f)
        )
        drawCircle(
            color = plantGreen,
            radius = 6.dp.toPx(),
            center = Offset(potLeft + potW * 0.3f, potTop - 6.dp.toPx())
        )
        drawCircle(
            color = plantGreen,
            radius = 7.dp.toPx(),
            center = Offset(potLeft + potW * 0.7f, potTop - 8.dp.toPx())
        )
    }
}
