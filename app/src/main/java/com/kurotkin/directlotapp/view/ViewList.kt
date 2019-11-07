package com.kurotkin.directlotapp.view

import android.view.View
import com.kurotkin.directlotapp.net.entity.LotLite
import com.kurotkin.directlotapp.presenter.LotListPresenter
import com.kurotkin.directlotapp.presenter.LotListPresenterImpl

interface ViewList {
    fun setContentView(view: View)
    fun onInflate(presenter: LotListPresenter)
    fun addLots(lots : List<LotLite>)
    fun notifyAdapter()
    fun showProgress()
    fun hideProgress()
    fun showErrorMessage(error: String?)
    fun refresh(lots: List<LotLite>)
}