package com.kurotkin.directlotapp.model

import com.kurotkin.directlotapp.App
import com.kurotkin.directlotapp.model.net.DirectlotService
import com.kurotkin.directlotapp.model.net.entity.LotFromServer
import io.reactivex.Single

class LotsRepositoryImpl(val apiService: DirectlotService) : LotsRepository {

    init {
        App.appComponent.inject(this)
    }

    override fun getLot(id: Long) : Single<LotFromServer> {
        return apiService.getOneLot(id)
    }

}