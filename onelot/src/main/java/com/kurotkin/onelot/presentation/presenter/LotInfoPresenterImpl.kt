package com.kurotkin.directlotapp.presentation.one_lot.presenter

import android.annotation.SuppressLint
import com.kurotkin.core.di.CoreInjectHelper
import com.kurotkin.directlotapp.di.DaggerOnelotComponent
import com.kurotkin.directlotapp.domain.LotsUserCase
import com.kurotkin.directlotapp.presentation.one_lot.view.ViewInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.properties.Delegates

class LotInfoPresenterImpl : LotInfoPresenter {

    @Inject
    lateinit var lotsUserCase: LotsUserCase

    lateinit var view: ViewInfo
    lateinit var listener: LotInfoPresenter.OnGoToWeb
    var id by Delegates.notNull<Long>()

    init {
        DaggerOnelotComponent.builder()
            .build()
            .inject(this)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(id: Long) {
        this.id = id
        view.showLoader()
        lotsUserCase.lot(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.setLot(it)
                view.hideLoader()
            }, {
                view.hideLoader()
            })
    }

    override fun onGoToWeb(url: String) {
        listener.onGoToWeb(url)
    }

    override fun attachView(view: ViewInfo) {
        this.view = view
    }

    override fun attachListener(listener: LotInfoPresenter.OnGoToWeb) {
        this.listener = listener
    }
}