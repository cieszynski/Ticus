package de.cieszynski.ticus.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import de.cieszynski.ticus.ui.home.HomeScreen

@Composable
fun NavPane(selectedIndex: MutableState<Int>, modifier: Modifier) {
    Box(modifier=modifier) {
        when (selectedIndex.value) {
            1 -> Text(text = "OK Fav")
            2 -> Text(text = "OK Mehr")
            else -> HomeScreen()
        }
    }
}