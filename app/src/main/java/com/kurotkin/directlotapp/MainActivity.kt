package com.kurotkin.directlotapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.kurotkin.directlotapp.crypto.CryptoPouch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token = CryptoPouch.getToken()
        Log.i("Crypto", "Crypto token = $token")

    }
}
