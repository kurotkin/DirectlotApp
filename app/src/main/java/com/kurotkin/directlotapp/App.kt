package com.kurotkin.directlotapp

import android.app.Application
import com.kurotkin.directlotapp.di.AppComponent
import com.kurotkin.directlotapp.di.AppModule
import com.kurotkin.directlotapp.di.DaggerAppComponent
import com.squareup.picasso.Picasso

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        val builder = Picasso.Builder(this)
        Picasso.setSingletonInstance(builder.build())

        appComponent = DaggerAppComponent.builder().build()
    }
}