package com.kurotkin.directlotapp.view

import com.kurotkin.directlotapp.net.entity.LotLite

interface ViewList {
    fun addLots(lots : List<LotLite>)
    fun notifyAdapter()
    fun showProgress()
    fun hideProgress()
    fun showErrorMessage(error: String?)
    fun refresh(lots: List<LotLite>)
}