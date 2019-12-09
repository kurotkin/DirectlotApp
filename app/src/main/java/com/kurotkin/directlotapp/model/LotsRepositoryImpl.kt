package com.kurotkin.directlotapp.model

import com.kurotkin.core.di.CoreInjectHelper
import com.kurotkin.directlotapp.App
import com.kurotkin.directlotapp.di.DaggerListLotsComponent
import com.kurotkin.directlotapp.di.RepositoryModule
import com.kurotkin.directlotapp.model.net.DirectlotService
import com.kurotkin.directlotapp.model.net.entity.LotFromServer
import com.kurotkin.directlotapp.model.net.entity.LotLiteFromServer
import io.reactivex.Single

class LotsRepositoryImpl(val apiService: DirectlotService) : LotsRepository {

    init {
        DaggerListLotsComponent.builder()
            .repositoryModule(RepositoryModule())
            .build()
            .inject(this)
    }

    override fun getLot(id: Long) : Single<LotFromServer> {
        return apiService.getOneLot(id)
    }

    override fun getCurrentLots() : Single<List<LotLiteFromServer>> {
        return apiService.getLastLiteLots()
    }
}