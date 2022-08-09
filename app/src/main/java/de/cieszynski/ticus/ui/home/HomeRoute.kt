package de.cieszynski.ticus.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable

// https://medium.com/androiddevelopers/jetnews-for-every-screen-4d8e7927752
// https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/home/HomeViewModel.kt
// https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/JetnewsNavGraph.kt
// https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/home/HomeScreens.kt
// https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/home/HomeRoute.kt

@Composable
fun HomeRoute(homeViewModel: HomeViewModel, isCompact: Boolean) {
    HomeRoute(
        isExpandedScreen = !isCompact,
        onSelectPost = { homeViewModel.selectArticle(it) },
    )
}

@Composable
fun HomeRoute(
    isExpandedScreen: Boolean,
    onSelectPost: (String) -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val homeListLazyListState = rememberLazyListState()
    val homeScreenType = getHomeScreenType(isExpandedScreen/*, uiState*/)

    when (homeScreenType) {
        HomeScreenType.FeedWithArticleDetails -> {
            HomeFeedWithArticleDetailsScreen(onSelectPost = onSelectPost)
        }
        HomeScreenType.Feed -> {
            HomeFeedScreen(onSelectPost = onSelectPost)
        }
        HomeScreenType.ArticleDetails -> {
            ArticleScreen()
            BackHandler {
                //onInteractWithFeed()
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
    /*uiState: HomeUiState*/
): HomeScreenType = when (isExpandedScreen) {
    false -> {
        if (/*uiState.isArticleOpen*/false) {
            HomeScreenType.ArticleDetails
        } else {
            HomeScreenType.Feed
        }
    }
    true -> HomeScreenType.FeedWithArticleDetails
}