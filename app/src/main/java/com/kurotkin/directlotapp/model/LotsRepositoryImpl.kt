package com.kurotkin.directlotapp.model

import com.kurotkin.directlotapp.App
import com.kurotkin.directlotapp.model.net.DirectlotService
import com.kurotkin.directlotapp.model.net.entity.LotFromServer
import com.kurotkin.directlotapp.model.net.entity.LotLiteFromServer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LotsRepositoryImpl : LotsRepository {

    init {
        App.appComponent.inject(this)
    }

    override fun getLot(callback: (LotFromServer) -> Unit, id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = DirectlotService()
            val result = async { apiService.getOneLot(id) }
            val currentResponse = result.await()
            launch(Dispatchers.Main) {
                val lot = currentResponse.await()
                callback.invoke(lot)
            }
        }
    }

    override fun getCurrentLots(callback: (List<LotLiteFromServer>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = DirectlotService()
            val result = async { apiService.getLastLiteLots() }
            val currentResponse = result.await()
            launch(Dispatchers.Main) {
                val lots = currentResponse.await()
                callback.invoke(lots)
            }
        }
    }
}