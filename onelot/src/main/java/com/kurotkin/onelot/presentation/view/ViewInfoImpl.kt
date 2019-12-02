package com.kurotkin.directlotapp.presentation.one_lot.view

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.kurotkin.directlotapp.domain.entity.Lot
import com.kurotkin.directlotapp.presentation.one_lot.presenter.LotInfoPresenter
import com.kurotkin.onelot.R
import com.squareup.picasso.Picasso

class ViewInfoImpl : ViewInfo {

    override lateinit var presenter: LotInfoPresenter
    private lateinit var rootView: View
    lateinit var recyclerAdapter: PhotosRecycleAdapter

    override fun setContentView(view: View) {
        rootView = view
    }

    @SuppressLint("SetTextI18n")
    override fun setLot(lot: Lot) {
        val lotName = rootView.findViewById<TextView>(R.id.lotName)
        val textPrice = rootView.findViewById<TextView>(R.id.textPrice)
        val textInfo = rootView.findViewById<TextView>(R.id.textInfo)
        val textPlace = rootView.findViewById<TextView>(R.id.textPlace)
        val photoList = rootView.findViewById<RecyclerView>(R.id.lotPhotos)
        val imageFlag = rootView.findViewById<ImageView>(R.id.imageFlag)
        val goToWebButton = rootView.findViewById<Button>(R.id.go_to_web)

        lotName.text = lot.name
        textPrice.text = "${lot.price} ${rootView.context.getString(R.string.rub)}"
        val info = rootView.context.getString(R.string.lot_mess)
        textInfo.text = java.lang.String.format(info, lot.id.toString(), lot.date.split("T")[0], lot.goods.toString(), lot.goodsSold.toString())
        textPlace.text = lot.location

        recyclerAdapter = PhotosRecycleAdapter()
        recyclerAdapter.setData(lot.picUrls)

        photoList.layoutManager = LinearLayoutManager(rootView.context, LinearLayoutManager.HORIZONTAL, false)
        photoList.addItemDecoration(LinePagerIndicatorDecoration())
        photoList.adapter = recyclerAdapter

        Picasso.get()
            .load(lot.countryImg)
            .placeholder(R.drawable.no_image)
            .error(R.drawable.no_photo_error)
            .fit()
            .centerCrop()
            .into(imageFlag)

        goToWebButton.setOnClickListener{
            presenter.onGoToWeb(lot.url)
        }
    }

    override fun showLoader() {
        val loader = rootView.findViewById<LoaderView>(R.id.loader)
        loader.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        val loader = rootView.findViewById<LoaderView>(R.id.loader)
        loader.visibility = View.GONE
    }

}