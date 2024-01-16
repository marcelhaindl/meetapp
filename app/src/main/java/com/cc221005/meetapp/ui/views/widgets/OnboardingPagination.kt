package com.cc221005.meetapp.ui.views.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cc221005.meetapp.ui.views.Screen

@Composable
fun OnboardingPagination(selectedScreen: Screen) {
    val selectedModifier = Modifier
        .size(8.dp)
        .clip(shape = RoundedCornerShape(4.dp))
        .background(color = MaterialTheme.colorScheme.primary)

    val unselectedModifier = Modifier
        .size(8.dp)
        .clip(shape = RoundedCornerShape(4.dp))
        .background(color = MaterialTheme.colorScheme.primaryContainer)

    Row {
        Box(modifier = if(selectedScreen == Screen.OnboardingFlow1) selectedModifier else unselectedModifier)
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = if(selectedScreen == Screen.OnboardingFlow2) selectedModifier else unselectedModifier)
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = if(selectedScreen == Screen.OnboardingFlow3) selectedModifier else unselectedModifier)
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = if(selectedScreen == Screen.OnboardingFlow4) selectedModifier else unselectedModifier)
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = if(selectedScreen == Screen.OnboardingFlow5) selectedModifier else unselectedModifier)
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = if(selectedScreen == Screen.OnboardingFlow6) selectedModifier else unselectedModifier)
    }
}