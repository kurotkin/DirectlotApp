package com.kurotkin.directlotapp.presentation.list_of_lots.view

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.kurotkin.directlotapp.R
import com.kurotkin.directlotapp.domain.entity.LotLite
import com.kurotkin.directlotapp.presentation.list_of_lots.presenter.LotListPresenter

class ViewListImpl : ViewList {

    lateinit var rootView: View
    lateinit var presenter: LotListPresenter
    lateinit var lotList: RecyclerView
    lateinit var swipeContainer: SwipeRefreshLayout
    lateinit var recyclerAdapter: LiteLotRecyclerAdapter

    override fun setContentView(view: View) {
        rootView = view
    }

    override fun onInflate(presenter: LotListPresenter) {
        this.presenter = presenter
        recyclerAdapter =
            LiteLotRecyclerAdapter(
                presenter
            )

        lotList = rootView.findViewById(R.id.lotList)
        lotList.layoutManager = LinearLayoutManager(rootView.context)
        lotList.adapter = recyclerAdapter

        swipeContainer = rootView.findViewById(R.id.swipe_container)
        swipeContainer.setOnRefreshListener { presenter.onRefreshList() }
    }

    override fun addLots(lots: List<LotLite>) {
        recyclerAdapter.setData(lots)
    }

    override fun showProgress() {
        swipeContainer.isRefreshing = false
    }

    override fun hideProgress() {
        swipeContainer.isRefreshing = false
    }

    override fun showErrorMessage() {
        val ms = AlertDialog.Builder(rootView.context)
        ms.setTitle(rootView.context.getString(R.string.net_err_title))
        ms.setIcon(R.drawable.kisspng)
        ms.setMessage(rootView.context.getString(R.string.net_err_mess))
        ms.setPositiveButton(
            rootView.context.getString(R.string.yes)
        ) { dialog, arg1 -> }
        ms.show()
    }

    override fun refresh(lots: List<LotLite>) {
        recyclerAdapter.setData(lots)
        hideProgress()
    }
}