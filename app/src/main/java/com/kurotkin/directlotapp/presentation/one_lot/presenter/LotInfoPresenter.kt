package com.kurotkin.directlotapp.presentation.one_lot.presenter

import com.kurotkin.directlotapp.presentation.one_lot.view.ViewInfo

interface LotInfoPresenter {
    fun onViewCreated()
    fun onGoToWeb(url: String)
    fun attachView(view: ViewInfo)
    fun attachListener(listener: OnGoToWeb)
    fun flashId(id: Long)

    interface OnGoToWeb{
        fun onGoToWeb(url: String)
    }
}