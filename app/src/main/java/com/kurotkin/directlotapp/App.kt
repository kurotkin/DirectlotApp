package com.kurotkin.directlotapp

import android.app.Application
import com.squareup.picasso.Picasso

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val builder = Picasso.Builder(this)
        Picasso.setSingletonInstance(builder.build())
    }
}