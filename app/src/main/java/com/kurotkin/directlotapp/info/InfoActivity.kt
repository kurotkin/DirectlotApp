package com.kurotkin.directlotapp.info

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kurotkin.directlotapp.R
import com.kurotkin.directlotapp.net.DirectlotService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info.*
import android.net.Uri
import java.text.SimpleDateFormat


class InfoActivity : AppCompatActivity() {

    companion object{
        val LOG_ID = "log id"
        fun getInstance(context: Context, id: Long): Intent{
            val intent = Intent(context, InfoActivity::class.java)
            intent.putExtra(LOG_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val id = intent.getSerializableExtra(LOG_ID) as Long
        coroutine(id)
    }

    fun coroutine(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = DirectlotService()
            val result = async { apiService.getOneLot(id) }
            val currentResponse = result.await()
            launch(Dispatchers.Main) {
                val lot = currentResponse.await()
                lotName.text = lot.name
                textPrice.text = "${lot.price} \u20BD"
                textInfo.text = "Лот ${lot.id}, добавлен " +
                        "${lot.date.split("T")[0]}\n" +
                        "В наличии / в запросах покупки: ${lot.goods} / ${lot.goodsSold}"
                textPlace.text = lot.location
                Picasso.get().load(lot.photoUrl)
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_photo_error)
                    .fit()
                    .centerCrop()
                    .into(imageView)
                Picasso.get().load(lot.countryImg)
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_photo_error)
                    .fit()
                    .centerCrop()
                    .into(imageFlag)
                button.setOnClickListener{
                    val i = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(lot.url)
                    )
                    startActivity(i)
                }
            }
        }
    }

}
