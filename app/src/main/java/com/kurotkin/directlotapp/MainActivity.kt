package com.kurotkin.directlotapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import com.kurotkin.directlotapp.crypto.CryptoPouch
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cryptoPouch = CryptoPouch()

        // Original text
        val testText = "А у нас сегодня кошка родила вчера котят"
        textViewOriginal.text = "[ORIGINAL]:\n$testText\n"

        // Encode the original data with RSA private key
        var encodedBytes: ByteArray? = cryptoPouch.encode(testText)
        textViewEncoded.text = "[ENCODED]:\n" + Base64.encodeToString(encodedBytes, Base64.DEFAULT) + "\n"




        // Decode the encoded data with RSA public key
        var decodedBytes: ByteArray? = cryptoPouch.decode(encodedBytes!!)
        textViewDecoded.text = "[DECODED]:\n" + String(decodedBytes!!) + "\n"
    }
}
