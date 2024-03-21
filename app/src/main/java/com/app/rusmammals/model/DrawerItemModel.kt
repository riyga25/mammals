package com.app.rusmammals.model

import cafe.adriel.voyager.core.screen.Screen

data class DrawerItemModel(
    val title: Int,
    val icon: Int,
    val screen: Screen
)