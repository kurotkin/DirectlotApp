package com.kurotkin.directlotapp.model

import com.kurotkin.directlotapp.model.net.entity.LotFromServer
import com.kurotkin.directlotapp.model.net.entity.LotLiteFromServer
import io.reactivex.Single

interface LotsRepository {
    fun getCurrentLots() : Single<List<LotLiteFromServer>>
    fun getLot(id: Long) : Single<LotFromServer>
}