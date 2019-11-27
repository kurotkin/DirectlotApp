package com.kurotkin.directlotapp.presentation.list_of_lots.presenter

import com.kurotkin.directlotapp.presentation.list_of_lots.view.LiteLotRecyclerAdapter
import com.kurotkin.directlotapp.presentation.list_of_lots.view.ViewList

interface LotListPresenter :
    LiteLotRecyclerAdapter.OnClickListener {
    fun onRefreshList()
    fun onMakeList()
    fun onOpenLot(id: Long)
    fun attachView(view: ViewList)
    fun attachListener(listener: OnClickGetInfoListener)
}