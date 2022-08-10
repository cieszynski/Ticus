package de.cieszynski.ticus.model

data class PostsFeed(
    val recentPosts: List<Post>
) {
    val allPosts: List<Post> = recentPosts
}