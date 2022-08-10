package de.cieszynski.ticus.data

import de.cieszynski.ticus.data.posts.posts
import de.cieszynski.ticus.model.PostsFeed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsRepository {
    suspend fun getPostsFeed(): Result<PostsFeed> {
        return withContext(Dispatchers.IO) {
            //delay(800) // pretend we're on a slow network
            //if (shouldRandomlyFail()) {
            //    Result.Error(IllegalStateException())
            //} else {
                Result.Success(posts)
            //}
        }
    }
}