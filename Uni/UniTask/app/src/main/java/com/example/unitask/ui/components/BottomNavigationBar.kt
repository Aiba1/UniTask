package com.example.unitask.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitask.data.model.NavItem
import com.example.unitask.ui.theme.Spacing

@Composable
fun BottomNavigationBar(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Spacing.small, horizontal = Spacing.small),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val navItems = listOf(
                NavItem("Home", Icons.Outlined.Home),
                NavItem("Schedule", Icons.Outlined.CalendarToday),
                NavItem("Tasks", Icons.Outlined.TaskAlt),
                NavItem("Classes", Icons.AutoMirrored.Outlined.MenuBook)
            )

            navItems.forEach { item ->
                val isSelected = selectedTab == item.label
                val activeBgColor = if (isSelected) MaterialTheme.colorScheme.tertiary else Color.Transparent
                val activeContentColor = if (isSelected) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = activeBgColor,
                    modifier = Modifier
                        .defaultMinSize(minWidth = Spacing.minTouchTarget, minHeight = Spacing.minTouchTarget)
                        .clickable { onTabSelected(item.label) }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = Spacing.medium, vertical = Spacing.small),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = activeContentColor,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = item.label,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = activeContentColor
                        )
                    }
                }
            }
        }
    }
}
