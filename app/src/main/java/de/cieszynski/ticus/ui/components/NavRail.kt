package de.cieszynski.ticus.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.cieszynski.ticus.ui.NavigationItem

// https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/components/AppNavRail.kt
@Composable
fun NavRail(
    items: List<NavigationItem>,
    selectedIndex: MutableState<Int>,
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null
) {
    NavigationRail(
        modifier = modifier,
        header = header
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    icon = { Icon(imageVector = item.icon, contentDescription = "") },
                    label = { Text(stringResource(item.label)) },
                    selected = selectedIndex.value == index,
                    onClick = { selectedIndex.value = index }
                )
            }
        }
    }
}