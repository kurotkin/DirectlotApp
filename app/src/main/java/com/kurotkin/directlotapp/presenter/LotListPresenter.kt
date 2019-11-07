package com.kurotkin.directlotapp.presenter

import com.kurotkin.directlotapp.list.LiteLotRecyclerAdapter
import com.kurotkin.directlotapp.view.ViewList

interface LotListPresenter : LiteLotRecyclerAdapter.OnClickListener {
    fun onRefreshList()
    fun onMakeList()
    fun onOpenLot(id: Long)
    fun attachView(view: ViewList)
    fun attachListener(listener: OnClickGetInfoListener)
}