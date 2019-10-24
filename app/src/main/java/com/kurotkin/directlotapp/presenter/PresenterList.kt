package com.kurotkin.directlotapp.presenter

import com.kurotkin.directlotapp.view.ViewList

interface PresenterList<V> {
    fun attach(view: V)
}

abstract class Presenter {
    protected lateinit var view: ViewList

    abstract fun refreshList()

    fun attach(view: ViewList) {
        this.view = view
    }
}