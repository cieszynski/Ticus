package de.cieszynski.ticus

import android.app.Application
import de.cieszynski.ticus.data.AppContainer

class TicusApplication : Application() {

    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}