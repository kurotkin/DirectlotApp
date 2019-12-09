package com.kurotkin.directlotapp.presentation.presenter

import com.kurotkin.directlotapp.presentation.view.LiteLotRecyclerAdapter
import com.kurotkin.directlotapp.presentation.view.ViewList

interface LotListPresenter :
    LiteLotRecyclerAdapter.OnClickListener {
    fun onRefreshList()
    fun onMakeList()
    fun onOpenLot(id: Long)
    fun attachView(view: ViewList)
    fun attachListener(listener: OnClickGetInfoListener)
}