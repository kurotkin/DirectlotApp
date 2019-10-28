package com.kurotkin.directlotapp.presenter

interface LotInfoPresenter {
    fun onViewCreated()
    fun onGoToWeb(url: String)

    interface OnGoToWeb{
        fun onGoToWeb(url: String)
    }
}