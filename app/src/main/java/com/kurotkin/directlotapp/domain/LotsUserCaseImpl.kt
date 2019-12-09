package com.kurotkin.directlotapp.domain

import com.kurotkin.directlotapp.di.DaggerListLotsComponent
import com.kurotkin.directlotapp.di.DomainModule
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
        DaggerListLotsComponent.builder()
            .domainModule(DomainModule())
            .build()
            .inject(this)
    }

    override fun currentLots() : Single<List<LotLite>> {
        return Single.create { emiter ->
            repository.getCurrentLots().subscribe({
                emiter.onSuccess(
                    ConvertUtil().convertLotsLiteFromServer(it)
                )
            },{
                emiter.onError(it)
            })
        }
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