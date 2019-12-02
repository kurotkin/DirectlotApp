package com.kurotkin.core

import com.kurotkin.core.Navigation

interface NavigationProvider {

    fun getNavigation(): Navigation
}