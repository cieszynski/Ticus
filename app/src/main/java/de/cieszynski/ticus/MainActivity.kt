package de.cieszynski.ticus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import de.cieszynski.ticus.ui.TicusApp

// https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#NavigationRail(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,kotlin.Function1,kotlin.Function1)
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as TicusApplication).container

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)

            TicusApp(
                appContainer = appContainer,
                windowSizeClass = windowSizeClass
            )
        }
    }
}