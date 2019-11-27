package com.kurotkin.directlotapp.model

import com.kurotkin.directlotapp.model.net.entity.LotFromServer
import com.kurotkin.directlotapp.model.net.entity.LotLiteFromServer

interface LotsRepository {
    fun getCurrentLots(callback: (List<LotLiteFromServer>) -> Unit)
    fun getLot(callback: (LotFromServer) -> Unit, id: Long)
}