package com.app.rusmammals.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.rusmammals.theme.MammalColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MammalTopBar(
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable() (RowScope.() -> Unit) = {}
) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = MammalColors.white,
            navigationIconContentColor = MammalColors.white,
            containerColor = Color.Transparent
        ),
        modifier = Modifier.background(Brush.horizontalGradient(MammalColors.mainGradient)),
        navigationIcon = navigationIcon,
        actions = actions
    )
}