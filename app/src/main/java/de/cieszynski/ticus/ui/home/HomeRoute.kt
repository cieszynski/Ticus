package de.cieszynski.ticus.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key

// https://medium.com/androiddevelopers/jetnews-for-every-screen-4d8e7927752
// https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/home/HomeViewModel.kt
// https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/JetnewsNavGraph.kt
// https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/home/HomeScreens.kt
// https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/home/HomeRoute.kt

@Composable
fun HomeRoute(homeViewModel: HomeViewModel, isCompact: Boolean) {
    // UiState of the HomeScreen
    val uiState by homeViewModel.uiState.collectAsState()
    HomeRoute(
        uiState = uiState,
        isExpandedScreen = !isCompact,
        onSelectPost = { homeViewModel.selectArticle(it) },
        onInteractWithFeed = { homeViewModel.interactedWithFeed() },
    )
}

@Composable
fun HomeRoute(
    uiState: HomeUiState,
    isExpandedScreen: Boolean,
    onSelectPost: (String) -> Unit,
    onInteractWithFeed: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val homeListLazyListState = rememberLazyListState()
    val articleDetailLazyListStates = when (uiState) {
        is HomeUiState.HasPosts -> uiState.postsFeed.allPosts
        is HomeUiState.NoPosts -> emptyList()
    }.associate { post ->
        key(post.id) {
            post.id to rememberLazyListState()
        }
    }
    val homeScreenType = getHomeScreenType(isExpandedScreen, uiState)

    when (homeScreenType) {
        HomeScreenType.FeedWithArticleDetails -> {
            HomeFeedWithArticleDetailsScreen(
                uiState = uiState,
                homeListLazyListState = homeListLazyListState,
                onSelectPost = onSelectPost
            )
        }
        HomeScreenType.Feed -> {
            HomeFeedScreen(
                uiState = uiState,
                homeListLazyListState = homeListLazyListState,
                onSelectPost = onSelectPost
            )
        }
        HomeScreenType.ArticleDetails -> {
            // Guaranteed by above condition for home screen type
            check(uiState is HomeUiState.HasPosts)

            ArticleScreen(
                post = uiState.selectedPost,
                onBack = onInteractWithFeed,
            )
            BackHandler {
                onInteractWithFeed()
            }
        }
    }
}


private enum class HomeScreenType {
    FeedWithArticleDetails,
    Feed,
    ArticleDetails
}

@Composable
private fun getHomeScreenType(
    isExpandedScreen: Boolean,
    uiState: HomeUiState
): HomeScreenType = when (isExpandedScreen) {
    false -> {
        when (uiState) {
            is HomeUiState.HasPosts -> {
                if (uiState.isArticleOpen) {
                    HomeScreenType.ArticleDetails
                } else {
                    HomeScreenType.Feed
                }
            }
            is HomeUiState.NoPosts -> HomeScreenType.Feed
        }
    }
    true -> HomeScreenType.FeedWithArticleDetails
}