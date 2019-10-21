package com.kurotkin.directlotapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import com.kurotkin.directlotapp.crypto.CryptoMakerImp
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cryptoPouch = CryptoMakerImp()

        // Original text
        val testText = "temp1jbudbw8b6vexdrtf93dnxaud"
        textViewOriginal.text = "[ORIGINAL]:\n$testText\n"

        // Encode the original data with RSA private key
        var encodedBytes: ByteArray? = cryptoPouch.encode(testText)
        textViewEncoded.text = "[ENCODED]:\n" + Base64.encodeToString(encodedBytes, Base64.DEFAULT) + "\n"
        Log.e("Crypto", Base64.encodeToString(encodedBytes, Base64.DEFAULT))
        Log.e("Crypto", cryptoPouch.getPublicKey())





        // Decode the encoded data with RSA public key
        var decodedBytes: ByteArray? = cryptoPouch.decode(encodedBytes!!)
        textViewDecoded.text = "[DECODED]:\n" + String(decodedBytes!!) + "\n"
    }
}
