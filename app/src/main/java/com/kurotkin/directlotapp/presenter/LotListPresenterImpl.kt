package com.kurotkin.directlotapp.presenter

import com.kurotkin.directlotapp.list.LiteLotRecyclerAdapter
import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.view.ViewList

class LotListPresenterImpl(
    view: ViewList,
    repository: LotsRepository,
    litener: OnClickGetInfoListener ) : LotListPresenter(view, repository, litener),
    LiteLotRecyclerAdapter.OnClickListener {

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