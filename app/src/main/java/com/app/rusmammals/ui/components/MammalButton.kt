package com.app.rusmammals.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.app.rusmammals.theme.MammalColors

@Composable
fun MammalButton(
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MammalColors.white,
        ),
        modifier = modifier
            .alpha(if (enabled) 1F else 0.3F)
            .background(
                Brush.horizontalGradient(MammalColors.mainGradient),
                RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(8.dp),
        enabled = enabled
    ) {
        Text(
            text = title,
            color = MammalColors.white,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}