package de.cieszynski.ticus.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeFeedWithArticleDetailsScreen(onSelectPost: (String) -> Unit) {
    HomeScreenWithList() {/*hasPostsUiState, */contentModifier ->
        Row(contentModifier) {
            ItemList(onArticleTapped = onSelectPost)
            Text("HomeFeedWithArticleDetailsScreen")
        }
    }
}

@Composable
fun HomeFeedScreen(onSelectPost: (String) -> Unit) {
    HomeScreenWithList() {/*hasPostsUiState, */contentModifier ->
        ItemList(onArticleTapped = onSelectPost)
    }
}

@Composable
fun ArticleScreen() {
    Text("ArticleScreen")
}

//--------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenWithList(
    hasPostsContent: @Composable (
       // uiState: HomeUiState.HasPosts,
        modifier: Modifier
    ) -> Unit
) {
    Scaffold() { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)
        hasPostsContent(/*uiState, */contentModifier)
        //Text(text = "HomeScreenWithList")
    }
}

@Composable
private fun ItemList(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    state: LazyListState = rememberLazyListState(),
    onArticleTapped: (postId: String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        state = state
    ) {
        item { Text(text = "item 1",modifier = Modifier.clickable(onClick = {
            onArticleTapped("item 1")
        })) }
        item { Text(text = "item 2") }
        item { Text(text = "item 3") }
    }
}