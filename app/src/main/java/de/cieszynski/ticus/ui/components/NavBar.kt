package de.cieszynski.ticus.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import de.cieszynski.ticus.ui.NavigationItem

@Composable
fun NavBar(
    items: List<NavigationItem>,
    selectedIndex: MutableState<Int>
) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = "") },
                label = { Text(stringResource(item.label)) },
                selected = selectedIndex.value == index,
                onClick = { selectedIndex.value = index }
            )
        }
    }
}