package com.kurotkin.directlotapp.model

import com.kurotkin.core.di.CoreInjectHelper
import com.kurotkin.directlotapp.di.DaggerOnelotComponent
import com.kurotkin.directlotapp.model.net.DirectlotService
import com.kurotkin.directlotapp.model.net.entity.LotFromServer
import io.reactivex.Single

class LotsRepositoryImpl(val apiService: DirectlotService) : LotsRepository {

    init {
        DaggerOnelotComponent.builder()
            .build()
            .inject(this)
    }

    override fun getLot(id: Long) : Single<LotFromServer> {
        return apiService.getOneLot(id)
    }

}