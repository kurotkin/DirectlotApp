package com.kurotkin.directlotapp.presenter

import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.view.ViewInfo

class LotInfoPresentrerImpl(
    val id: Long,
    val view: ViewInfo,
    val repository: LotsRepository,
    val listener: LotInfoPresenter.OnGoToWeb) : LotInfoPresenter {

    override fun onViewCreated() {
        repository.getLot({
            view.setLot(it)
        }, id)
    }

    override fun onGoToWeb(url: String) {
        listener.onGoToWeb(url)
    }
}