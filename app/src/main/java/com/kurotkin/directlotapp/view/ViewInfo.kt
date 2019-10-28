package com.kurotkin.directlotapp.view

import com.kurotkin.directlotapp.net.entity.Lot
import com.kurotkin.directlotapp.presenter.LotInfoPresenter

interface ViewInfo {
    var presenter: LotInfoPresenter

    fun setLot(lot: Lot)

    fun onFinishInflate(presenter: LotInfoPresenter){
        this.presenter = presenter
    }
}