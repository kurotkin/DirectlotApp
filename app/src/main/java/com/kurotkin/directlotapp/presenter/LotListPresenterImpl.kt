package com.kurotkin.directlotapp.presenter

import com.kurotkin.directlotapp.App
import com.kurotkin.directlotapp.list.LiteLotRecyclerAdapter
import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.view.ViewList
import javax.inject.Inject

class LotListPresenterImpl : LotListPresenter, LiteLotRecyclerAdapter.OnClickListener {

    @Inject
    lateinit var view: ViewList

    @Inject
    lateinit var repository: LotsRepository

    @Inject
    lateinit var litener: LotListPresenter.OnClickGetInfoListener

    init {
        App.appComponent.inject(this)
    }

    override fun onItemClick(id: Long) {
        onOpenLot(id)
    }

    override fun onMakeList() {
        view.showProgress()
        repository.getCurrentLots {
            view.addLots(it)
            view.hideProgress()
        }
    }

    override fun onRefreshList() {
        view.showProgress()
        repository.getCurrentLots {
            view.addLots(it)
            view.hideProgress()
        }
    }

    override fun onOpenLot(id: Long) {
        litener.onClickGetInfoListener(id)
    }
}