package de.cieszynski.ticus.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import de.cieszynski.ticus.data.PostsRepository
import de.cieszynski.ticus.data.Result
import de.cieszynski.ticus.model.Post
import de.cieszynski.ticus.model.PostsFeed
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface HomeUiState {

    val isLoading: Boolean

    data class NoPosts(
        override val isLoading: Boolean
    ) : HomeUiState

    data class HasPosts(
        val postsFeed: PostsFeed,
        val selectedPost: Post,
        val isArticleOpen: Boolean,
        override val isLoading: Boolean
    ) : HomeUiState
}

private data class HomeViewModelState(
    val isLoading: Boolean = false,
    val postsFeed: PostsFeed? = null,
    val selectedPostId: String? = null, // TODO back selectedPostId in a SavedStateHandle
    val isArticleOpen: Boolean = false,
) {
    fun toUiState(): HomeUiState =
        if (postsFeed == null) {
            HomeUiState.NoPosts(isLoading = isLoading)
        } else {
            HomeUiState.HasPosts(
                selectedPost = postsFeed.allPosts.find {
                    it.id == selectedPostId
                } ?: postsFeed.allPosts[0],//TODO
                postsFeed = postsFeed,
                isLoading = isLoading,
                isArticleOpen = isArticleOpen,
            )
        }
}

class HomeViewModel(
    private val postsRepository: PostsRepository
) : ViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))

    // UI state exposed to the UI
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        refreshPosts()

        // Observe for favorite changes in the repo layer
//        viewModelScope.launch {
//            postsRepository.observeFavorites().collect { favorites ->
//                viewModelState.update { it.copy(favorites = favorites) }
//            }
//        }
    }


    /**
     * Refresh posts and update the UI state accordingly
     */
    private fun refreshPosts() {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {

            val result = postsRepository.getPostsFeed()
            viewModelState.update {
                when (result) {
                    is Result.Success -> it.copy(postsFeed = result.data, isLoading = false)
                    is Result.Error -> {
                        it.copy(/*errorMessages = errorMessages, */isLoading = false)
                    }
                }
            }
        }
    }

    /**
     * Selects the given article to view more information about it.
     */
    fun selectArticle(postId: String) {
        // Treat selecting a detail as simply interacting with it
        interactedWithArticleDetails(postId)
    }

    /**
     * Notify that the user interacted with the feed
     */
    fun interactedWithFeed() {
        viewModelState.update {
            it.copy(isArticleOpen = false)
        }
    }

    /**
     * Notify that the user interacted with the article details
     */
    private fun interactedWithArticleDetails(postId: String) {
        Log.d("XXX", postId)
        viewModelState.update {
            it.copy(
                selectedPostId = postId,
                isArticleOpen = true
            )
        }
    }

    /**
     * Factory for HomeViewModel that takes PostsRepository as a dependency
     */
    companion object {
        fun provideFactory(
            postsRepository: PostsRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(postsRepository) as T
            }
        }
    }
}