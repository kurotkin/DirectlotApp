package com.kurotkin.directlotapp.domain

import com.kurotkin.directlotapp.App
import com.kurotkin.directlotapp.domain.entity.Lot
import com.kurotkin.directlotapp.domain.entity.LotLite
import com.kurotkin.directlotapp.domain.utils.ConvertUtil
import com.kurotkin.directlotapp.model.LotsRepository
import io.reactivex.Single
import javax.inject.Inject

class LotsUserCaseImpl : LotsUserCase{

    @Inject
    lateinit var repository: LotsRepository

    init {
        App.appComponent.inject(this)
    }

    override fun lot(id: Long) : Single<Lot> {
        return Single.create { emiter ->
            repository.getLot(id).subscribe({
                Thread.sleep(2000)      // Для просмотра загрузчика
                emiter.onSuccess(
                    ConvertUtil().convertLotFromServerToLot(it)
                )
            },{
                emiter.onError(it)
            })
        }
    }

}