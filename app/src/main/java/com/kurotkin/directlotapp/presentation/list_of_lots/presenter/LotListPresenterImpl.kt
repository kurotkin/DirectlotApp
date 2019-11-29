package com.kurotkin.directlotapp.presentation.list_of_lots.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.kurotkin.directlotapp.App
import com.kurotkin.directlotapp.domain.LotsUserCase
import com.kurotkin.directlotapp.presentation.list_of_lots.view.LiteLotRecyclerAdapter
import com.kurotkin.directlotapp.presentation.list_of_lots.view.ViewList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LotListPresenterImpl : LotListPresenter,
    LiteLotRecyclerAdapter.OnClickListener {

    @Inject
    lateinit var lotsUserCase: LotsUserCase

    lateinit var view: ViewList
    lateinit var listener: OnClickGetInfoListener

    init {
        App.appComponent.inject(this)
    }

    override fun attachView(view: ViewList) {
        this.view = view
    }

    override fun attachListener(listener: OnClickGetInfoListener) {
        this.listener = listener
    }

    override fun onItemClick(id: Long) {
        onOpenLot(id)
    }

    override fun onMakeList() {
        updateUI()
    }

    override fun onRefreshList() {
        updateUI()
    }

    @SuppressLint("CheckResult")
    private fun updateUI(){
        view.showProgress()
        lotsUserCase.currentLots()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.addLots(it)
                view.hideProgress()
            }, {
                Log.e("LotListPresenterImpl", it.toString())
                view.hideProgress()
                view.showErrorMessage()
            })
    }

    override fun onOpenLot(id: Long) {
        listener.onClickGetInfoListener(id)
    }
}