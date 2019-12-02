package com.kurotkin.directlotapp.domain

import com.kurotkin.directlotapp.domain.entity.Lot
import com.kurotkin.directlotapp.domain.entity.LotLite
import io.reactivex.Single

interface LotsUserCase {
    fun lot(id: Long) : Single<Lot>
}