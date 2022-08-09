package de.cieszynski.ticus.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface HomeUiState {

    val isLoading: Boolean

    data class NoPosts(
        override val isLoading: Boolean
    ) : HomeUiState

    data class HasPosts(
        override val isLoading: Boolean
    ) : HomeUiState
}

private data class HomeViewModelState(
    val isLoading: Boolean = false,
    val selectedPostId: String? = null, // TODO back selectedPostId in a SavedStateHandle
) {
    fun toUiState(): HomeUiState =HomeUiState.NoPosts(isLoading = isLoading)
}

class HomeViewModel(
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
       // refreshPosts()

        // Observe for favorite changes in the repo layer
//        viewModelScope.launch {
//            postsRepository.observeFavorites().collect { favorites ->
//                viewModelState.update { it.copy(favorites = favorites) }
//            }
//        }
    }
    /**
     * Selects the given article to view more information about it.
     */
    fun selectArticle(postId: String) {
        // Treat selecting a detail as simply interacting with it
        interactedWithArticleDetails(postId)
    }

    /**
     * Notify that the user interacted with the article details
     */
    private fun interactedWithArticleDetails(postId: String) {
        Log.d("XXX", postId)
        viewModelState.update {
            it.copy(
                selectedPostId = postId,
                //isArticleOpen = true
            )
        }
    }
}