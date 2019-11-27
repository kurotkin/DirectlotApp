package com.kurotkin.directlotapp.presentation.one_lot.view

import android.view.View
import com.kurotkin.directlotapp.domain.entity.Lot
import com.kurotkin.directlotapp.model.net.entity.LotFromServer
import com.kurotkin.directlotapp.presentation.one_lot.presenter.LotInfoPresenter

interface ViewInfo {
    var presenter: LotInfoPresenter

    fun setContentView(view: View)
    fun setLot(lot: Lot)
    fun onInflate(presenter: LotInfoPresenter){
        this.presenter = presenter
    }
}