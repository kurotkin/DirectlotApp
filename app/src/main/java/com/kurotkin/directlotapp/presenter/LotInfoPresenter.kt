package com.kurotkin.directlotapp.presenter

import com.kurotkin.directlotapp.view.ViewInfo

interface LotInfoPresenter {
    fun onViewCreated()
    fun onGoToWeb(url: String)
    //fun attach(view: ViewInfo)

    interface OnGoToWeb{
        fun onGoToWeb(url: String)
    }
}