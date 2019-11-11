package com.kurotkin.directlotapp.view

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.kurotkin.directlotapp.R
import com.kurotkin.directlotapp.net.entity.Lot
import com.kurotkin.directlotapp.presenter.LotInfoPresenter
import com.squareup.picasso.Picasso

class ViewInfoImpl : ViewInfo {

    override lateinit var presenter: LotInfoPresenter
    private lateinit var rootView: View

    override fun setContentView(view: View) {
        rootView = view
    }

    override fun setLot(lot: Lot) {
        val lotName = rootView.findViewById<TextView>(R.id.lotName)
        val textPrice = rootView.findViewById<TextView>(R.id.textPrice)
        val textInfo = rootView.findViewById<TextView>(R.id.textInfo)
        val textPlace = rootView.findViewById<TextView>(R.id.textPlace)
        val imageView = rootView.findViewById<ImageView>(R.id.imageView)
        val imageFlag = rootView.findViewById<ImageView>(R.id.imageFlag)
        val goToWebButton = rootView.findViewById<Button>(R.id.go_to_web)

        lotName.text = lot.name
        textPrice.text = "${lot.price} \u20BD"
        textInfo.text = "Лот ${lot.id}, добавлен " +
                "${lot.date.split("T")[0]}\n" +
                "В наличии / в запросах покупки: ${lot.goods} / ${lot.goodsSold}"
        textPlace.text = lot.location

        Picasso.get()
            .load(lot.photoUrl)
            .placeholder(R.drawable.no_image)
            .error(R.drawable.no_photo_error)
            .fit()
            .centerCrop()
            .into(imageView)

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

}