package com.kurotkin.directlotapp.presenter

import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.view.ViewList

abstract class Presenter {
    protected lateinit var view: ViewList
    protected lateinit var repository: LotsRepository

    abstract fun onRefreshList()
    abstract fun onMakeList()
    abstract fun onOpenLot(id: Long)

    fun attach(view: ViewList, repository: LotsRepository) {
        this.view = view
        this.repository = repository
    }
}