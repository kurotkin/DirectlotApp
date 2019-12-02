package com.kurotkin.directlotapp

import android.app.Application
import com.kurotkin.core.Navigation
import com.kurotkin.core.NavigationProvider
import com.kurotkin.directlotapp.di.AppComponent
import com.kurotkin.directlotapp.di.DaggerAppComponent
import com.squareup.picasso.Picasso

class App: Application(), NavigationProvider {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var navigation: Navigation
    }

    override fun onCreate() {
        super.onCreate()
        val builder = Picasso.Builder(this)
        Picasso.setSingletonInstance(builder.build())
        navigation = NavigationImpl(this)
        appComponent = DaggerAppComponent.builder().build()
    }

    override fun getNavigation(): Navigation {
        return navigation
    }

}