package com.example.unitask.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitask.ui.theme.Spacing

@Composable
fun ViewAllButton(onClick: () -> Unit = {}) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .defaultMinSize(minWidth = Spacing.minTouchTarget, minHeight = Spacing.minTouchTarget)
            .clickable { onClick() }
    ) {
        Text(
            text = "view all >",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = Spacing.medium, vertical = Spacing.small)
        )
    }
}
