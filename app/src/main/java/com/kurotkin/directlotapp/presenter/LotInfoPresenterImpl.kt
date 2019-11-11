package com.kurotkin.directlotapp.presenter

import com.kurotkin.directlotapp.App
import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.view.ViewInfo
import com.kurotkin.directlotapp.view.ViewList
import javax.inject.Inject
import kotlin.properties.Delegates

class LotInfoPresenterImpl : LotInfoPresenter {

    @Inject
    lateinit var repository: LotsRepository

    lateinit var view: ViewInfo
    lateinit var listener: LotInfoPresenter.OnGoToWeb
    var id by Delegates.notNull<Long>()

    init {
        App.appComponent.inject(this)
    }

    override fun onViewCreated() {
        repository.getLot({ view.setLot(it) }, id)
    }

    override fun onGoToWeb(url: String) {
        listener.onGoToWeb(url)
    }

    override fun attachView(view: ViewInfo) {
        this.view = view
    }

    override fun attachListener(listener: LotInfoPresenter.OnGoToWeb) {
        this.listener = listener
    }

    override fun flashId(id: Long) {
        this.id = id
    }
}