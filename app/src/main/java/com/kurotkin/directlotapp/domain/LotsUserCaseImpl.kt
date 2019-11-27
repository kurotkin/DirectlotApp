package com.kurotkin.directlotapp.domain

import com.kurotkin.directlotapp.App
import com.kurotkin.directlotapp.domain.entity.Lot
import com.kurotkin.directlotapp.domain.entity.LotLite
import com.kurotkin.directlotapp.domain.utils.ConvertUtil
import com.kurotkin.directlotapp.model.LotsRepository
import com.kurotkin.directlotapp.model.net.entity.LotFromServer
import com.kurotkin.directlotapp.model.net.entity.LotLiteFromServer
import io.reactivex.Single
import javax.inject.Inject

class LotsUserCaseImpl : LotsUserCase{

    @Inject
    lateinit var repository: LotsRepository

    init {
        App.appComponent.inject(this)
    }

    override fun currentLots() : Single<List<LotLite>> {
        return Single.create { emiter ->
            repository.getCurrentLots {
                emiter.onSuccess(
                    ConvertUtil().convertLotsLiteFromServer(it)
                )
            }
        }
    }

    override fun lot(id: Long) : Single<Lot> {
        return Single.create { emiter ->
            repository.getLot({
                emiter.onSuccess(
                    ConvertUtil().convertLotFromServerToLot(it)
                )
            }, id)
        }
    }

}