package com.kurotkin.directlotapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.kurotkin.directlotapp.net.DirectlotRepo
import com.kurotkin.directlotapp.net.DirectlotService
import com.kurotkin.directlotapp.net.OnLotCallback
import com.kurotkin.directlotapp.net.entity.Lot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Для проверки
        DirectlotRepo().getLots(object: OnLotCallback{
            override fun onLotCallback(data: List<Lot>?) {
                data?.let {
                    it.forEach { lot -> Log.d("App", lot.toString()) }
                }
            }

        })
    }
}
