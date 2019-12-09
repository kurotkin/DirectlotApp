package com.kurotkin.directlotapp

import android.app.Application
import com.kurotkin.core.Navigation
import com.kurotkin.core.NavigationProvider
import com.kurotkin.core.di.CoreComponent
import com.kurotkin.core.di.CoreComponentProvider
import com.kurotkin.core.di.DaggerCoreComponent
import com.squareup.picasso.Picasso

class App: Application(), NavigationProvider, CoreComponentProvider {

    companion object {
        lateinit var navigation: Navigation
    }

    private lateinit var coreComponent: CoreComponent

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent.builder().build()
        }
        return coreComponent
    }

    override fun onCreate() {
        super.onCreate()
        val builder = Picasso.Builder(this)
        Picasso.setSingletonInstance(builder.build())
        navigation = NavigationImpl(this)
    }

    override fun getNavigation(): Navigation {
        return navigation
    }

}