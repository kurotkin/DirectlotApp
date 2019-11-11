package com.kurotkin.directlotapp.view

import android.view.View
import com.kurotkin.directlotapp.net.entity.Lot
import com.kurotkin.directlotapp.presenter.LotInfoPresenter

interface ViewInfo {
    var presenter: LotInfoPresenter

    fun setContentView(view: View)
    fun setLot(lot: Lot)
    fun onInflate(presenter: LotInfoPresenter){
        this.presenter = presenter
    }
}