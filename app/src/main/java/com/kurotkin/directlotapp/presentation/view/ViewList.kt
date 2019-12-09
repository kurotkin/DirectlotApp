package com.kurotkin.directlotapp.presentation.view

import android.view.View
import com.kurotkin.directlotapp.domain.entity.LotLite
import com.kurotkin.directlotapp.presentation.presenter.LotListPresenter

interface ViewList {
    fun setContentView(view: View)
    fun onInflate(presenter: LotListPresenter)
    fun addLots(lots : List<LotLite>)
    fun showProgress()
    fun hideProgress()
    fun showErrorMessage()
    fun refresh(lots: List<LotLite>)
}