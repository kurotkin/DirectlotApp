package com.kurotkin.directlotapp.presenter

interface LotListPresenter {

    interface OnClickGetInfoListener {
        fun onClickGetInfoListener(id: Long)
    }

    fun onRefreshList()
    fun onMakeList()
    fun onOpenLot(id: Long)
}