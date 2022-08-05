package de.cieszynski.ticus.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import de.cieszynski.ticus.ui.theme.TicusTheme

// https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/JetnewsApp.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicusApp(windowSizeClass: WindowSizeClass) {
    TicusTheme {
        val isCompact = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

        Scaffold(
            topBar = {
                if (isCompact) {
                    SmallTopAppBar(
                        title = { Text("Simple TopAppBar") },
                        navigationIcon = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Localized description",
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Localized description",
                                )
                            }
                        },
                    )
                }
            },
            bottomBar = {
                if (isCompact) {
                    NavigationBar {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    Icons.Filled.Favorite,
                                    contentDescription = null
                                )
                            },
                            label = { Text("item") },
                            selected = true, // selectedItem == index,
                            onClick = { /*selectedItem = index*/ }
                        )

                    }
                }
            },
            content = { innerPadding ->
                Row(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .wrapContentSize()
                ) {
                    if(!isCompact) {
                        NavigationRail {
                            NavigationRailItem(
                                icon = {
                                    Icon(
                                        Icons.Filled.Favorite,
                                        contentDescription = null
                                    )
                                },
                                label = { Text("item") },
                                selected = true, // selectedItem == index,
                                onClick = { /*selectedItem = index*/ }
                            )
                        }
                    }
                    Text(
                        text = "Body content",
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            })
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun DefaultPreview() {
    //CompactScreen { Text(text = "OK2")}
    TicusApp(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(
                width = 360.dp,
                height = 640.dp
            )
        )
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, name = "Medium", widthDp = 840, heightDp = 640)
@Composable
fun MediumPreview() {
    TicusApp(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(
                width = 840.dp,
                height = 640.dp
            )
        )
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, name = "Expanded", widthDp = 1200, heightDp = 840)
@Composable
fun ExpandedPreview() {
    TicusApp(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(
                width = 1200.dp,
                height = 840.dp
            )
        )
    )
}