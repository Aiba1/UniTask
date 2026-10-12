package com.example.unitask.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitask.data.model.TaskItem
import com.example.unitask.ui.theme.Spacing

@Composable
fun TodayTasksSection(
    tasks: List<TaskItem>,
    onTaskToggle: (Int) -> Unit,
    onViewAllClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Today’s tasks",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            ViewAllButton(onClick = onViewAllClick)
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column {
                tasks.forEachIndexed { index, task ->
                    TaskRowItem(
                        task = task,
                        onToggle = { onTaskToggle(task.id) }
                    )
                    if (index < tasks.size - 1) {
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = Spacing.medium)
                        )
                    }
                }
            }
        }
    }
}
