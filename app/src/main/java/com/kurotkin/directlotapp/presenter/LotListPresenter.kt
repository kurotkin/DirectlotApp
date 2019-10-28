package com.kurotkin.directlotapp.presenter

import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.view.ViewList

abstract class LotListPresenter(
    val view: ViewList,
    val repository: LotsRepository,
    val litener: OnClickGetInfoListener) {

    interface OnClickGetInfoListener {
        fun onClickGetInfoListener(id: Long)
    }

    abstract fun onRefreshList()
    abstract fun onMakeList()
    abstract fun onOpenLot(id: Long)
}