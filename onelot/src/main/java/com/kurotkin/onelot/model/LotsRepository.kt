package com.kurotkin.directlotapp.model

import com.kurotkin.directlotapp.model.net.entity.LotFromServer
import io.reactivex.Single

interface LotsRepository {
    fun getLot(id: Long) : Single<LotFromServer>
}