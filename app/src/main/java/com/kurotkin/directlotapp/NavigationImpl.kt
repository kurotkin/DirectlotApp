package com.kurotkin.directlotapp

import android.content.Context
import android.content.Intent
import com.kurotkin.core.Navigation
import com.kurotkin.info.AboutActivity

class NavigationImpl(val context: Context) : Navigation {

    override fun getInfoScreen(): Intent {
        return Intent(context, AboutActivity::class.java)
    }
}