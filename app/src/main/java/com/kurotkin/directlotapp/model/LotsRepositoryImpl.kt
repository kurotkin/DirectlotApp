package com.kurotkin.directlotapp.model

import com.kurotkin.directlotapp.App
import com.kurotkin.directlotapp.model.net.DirectlotService
import com.kurotkin.directlotapp.model.net.entity.LotFromServer
import com.kurotkin.directlotapp.model.net.entity.LotLiteFromServer
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LotsRepositoryImpl : LotsRepository {

    init {
        App.appComponent.inject(this)
    }

    override fun getLot(id: Long) : Single<LotFromServer> {
        val apiService = DirectlotService()
        return apiService.getOneLot(id)
    }

    override fun getCurrentLots() : Single<List<LotLiteFromServer>> {
        val apiService = DirectlotService()
        return apiService.getLastLiteLots()
    }
}