package com.kurotkin.directlotapp.model

import com.kurotkin.directlotapp.net.entity.Lot
import com.kurotkin.directlotapp.net.entity.LotLite

interface LotsRepository {
    fun getCurrentLots(callback: (List<LotLite>) -> Unit)
    fun getLot(callback: (Lot) -> Unit, id: Long)
}