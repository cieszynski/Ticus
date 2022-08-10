package de.cieszynski.ticus.data

import android.content.Context

class AppContainer(private val applicationContext: Context) {

    val postsRepository: PostsRepository by lazy {
        PostsRepository()
    }
}