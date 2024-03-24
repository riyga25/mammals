package com.app.rusmammals.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.app.rusmammals.theme.MammalColors

@Composable
fun FullScreenLoader(
    visible: Boolean
) {
    if (!visible) return
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.1F)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MammalColors.color1
        )
    }
}