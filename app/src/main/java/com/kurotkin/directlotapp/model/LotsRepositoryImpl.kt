package com.kurotkin.directlotapp.model

import com.kurotkin.directlotapp.net.DirectlotService
import com.kurotkin.directlotapp.net.entity.Lot
import com.kurotkin.directlotapp.net.entity.LotLite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LotsRepositoryImpl : LotsRepository {

    override fun getLot(callback: (Lot) -> Unit, id: Long) {
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

    override fun getCurrentLots(callback: (List<LotLite>) -> Unit) {
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