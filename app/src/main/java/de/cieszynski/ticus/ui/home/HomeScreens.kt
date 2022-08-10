package de.cieszynski.ticus.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Colors
import androidx.compose.material.TopAppBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.cieszynski.ticus.data.PostsRepository
import de.cieszynski.ticus.data.Result
import de.cieszynski.ticus.model.Post
import de.cieszynski.ticus.model.PostsFeed
import de.cieszynski.ticus.ui.theme.TicusTheme
import kotlinx.coroutines.runBlocking

@Composable
fun HomeFeedWithArticleDetailsScreen(
    uiState: HomeUiState,
    homeListLazyListState: LazyListState,
    onSelectPost: (String) -> Unit
) {
    HomeScreenWithList(
        uiState = uiState,
        homeListLazyListState = homeListLazyListState,
    ) { hasPostsUiState, contentModifier ->
        Row(contentModifier) {
            ItemList(
                postsFeed = hasPostsUiState.postsFeed,
                onArticleTapped = onSelectPost
            )
            Text("HomeFeedWithArticleDetailsScreen")
        }
    }
}

@Composable
fun HomeFeedScreen(
    uiState: HomeUiState,
    homeListLazyListState: LazyListState,
    onSelectPost: (String) -> Unit
) {
    HomeScreenWithList(
        uiState = uiState,
        homeListLazyListState = homeListLazyListState,
    ) { hasPostsUiState, contentModifier ->
        ItemList(
            postsFeed = hasPostsUiState.postsFeed,
            onArticleTapped = onSelectPost
        )
    }
}

// https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/article/ArticleScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    post: Post,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = post.title, color=Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
            )
        },

        ) { innerPadding ->
        Text("ArticleScreen", modifier = Modifier.padding(innerPadding))
    }

}

//--------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenWithList(
    uiState: HomeUiState,
    homeListLazyListState: LazyListState,
    hasPostsContent: @Composable (
        uiState: HomeUiState.HasPosts,
        modifier: Modifier
    ) -> Unit
) {
    Scaffold() { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)

        when (uiState) {
            is HomeUiState.HasPosts -> hasPostsContent(uiState, contentModifier)
            is HomeUiState.NoPosts -> {
                Text("Irgendwas")
            }
        }
        // hasPostsContent(uiState, contentModifier)
        //Text(text = "HomeScreenWithList")
    }
}

@Composable
private fun ItemList(
    postsFeed: PostsFeed,
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
        item { ItemListAll(postsFeed.recentPosts, onArticleTapped) }
    }
}

@Composable
private fun ItemListAll(
    posts: List<Post>,
    onArticleTapped: (String) -> Unit
) {
    Column {
        posts.forEach { post ->
            Text(text = post.title, modifier = Modifier.clickable(onClick = {
                onArticleTapped(post.id)
            }))
        }
    }
}

@Preview("Home list detail screen", device = Devices.PIXEL_C)
@Composable
fun PreviewHomeListDetailScreen() {
    val postsFeed = runBlocking {
        (PostsRepository().getPostsFeed() as Result.Success).data
    }
    TicusTheme() {
        HomeFeedWithArticleDetailsScreen(
            uiState = HomeUiState.HasPosts(
                postsFeed = postsFeed,
                selectedPost = postsFeed.allPosts[0],
                isArticleOpen = false,
                isLoading = false,
            ),
            homeListLazyListState = rememberLazyListState(),
            onSelectPost = {},
        )
    }
}