package com.app.rusmammals.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.rusmammals.R
import com.app.rusmammals.model.DrawerItemModel
import com.app.rusmammals.theme.MammalColors
import com.app.rusmammals.ui.settings.SettingsScreen
import com.app.rusmammals.ui.feedback.FeedbackScreen
import com.app.rusmammals.ui.species.SpeciesScreen
import kotlinx.coroutines.launch

object MainScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val localFocusManager = LocalFocusManager.current

        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer(drawerState = drawerState)
            },
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus(true)
                })
            },
            drawerState = drawerState
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(id = R.string.menu_entries)) },
                        colors = TopAppBarDefaults.topAppBarColors(
                            titleContentColor = MammalColors.white,
                            navigationIconContentColor = MammalColors.white,
                            containerColor = Color.Transparent
                        ),
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            ) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = "Drawer",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                        modifier = Modifier.background(Brush.horizontalGradient(MammalColors.mainGradient))
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {},
                        containerColor = MammalColors.color2,
                        contentColor = MammalColors.white
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Добавить",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            ) { paddings ->
                Column(Modifier.padding(paddings)) {
                    Text(text = "Hello world!")
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun AppDrawer(drawerState: DrawerState) {
    val navigator = LocalNavigator.current
    val scope = rememberCoroutineScope()

    fun selectItem(item: DrawerItemModel) {
        scope.launch {
            drawerState.close()
            navigator?.push(item.screen)
        }
    }

    ModalDrawerSheet(
        modifier = Modifier.widthIn(max = 300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .statusBarsPadding()
        ) {
            drawerItems.forEach { item ->
                NavigationDrawerItem(
                    label = { Text(text = stringResource(item.title)) },
                    selected = item.screen == navigator?.lastItem,
                    onClick = { selectItem(item) },
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
            }
        }
    }
}

val drawerItems: List<DrawerItemModel> = listOf(
    DrawerItemModel(
        title = R.string.menu_entries,
        icon = R.drawable.ic_format_list_bulleted_black_24dp,
        screen = SpeciesScreen
    ),
//    DrawerItemModel(
//        title = "Карта",
//        icon = R.drawable.ic_format_list_bulleted_black_24dp,
//        screen = SpeciesScreen
//    ),
    DrawerItemModel(
        title = R.string.menu_settings,
        icon = R.drawable.ic_settings_black_24dp,
        screen = SettingsScreen
    ),
    DrawerItemModel(
        title = R.string.menu_feedback,
        icon = R.drawable.ic_message_black_24dp,
        screen = FeedbackScreen
    ),
//    DrawerItemModel(
//        title = "Информация",
//        icon = R.drawable.ic_message_black_24dp,
//        screen = SpeciesScreen
//    ),
//    DrawerItemModel(
//        title = "Мои объекты",
//        icon = R.drawable.ic_m_marker_24dp,
//        screen = SpeciesScreen
//    )
)