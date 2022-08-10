package de.cieszynski.ticus.data.posts

import de.cieszynski.ticus.model.Post
import de.cieszynski.ticus.model.PostsFeed

val posts: PostsFeed = PostsFeed(
    recentPosts = listOf(
        Post(id = "1", title = "Title 1"),
        Post(id = "2", title = "Title 2"),
        Post(id = "3", title = "Title 3"),
        Post(id = "4", title = "Title 4"),
        Post(id = "5", title = "Title 5")
    )
)