package com.kurotkin.directlotapp.presenter

import com.kurotkin.directlotapp.App
import com.kurotkin.directlotapp.list.LiteLotRecyclerAdapter
import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.view.ViewList
import javax.inject.Inject

class LotListPresenterImpl(var view: ViewList, var litener: LotListPresenter.OnClickGetInfoListener) : LotListPresenter, LiteLotRecyclerAdapter.OnClickListener {

    @Inject
    lateinit var repository: LotsRepository

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