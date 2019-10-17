package com.kurotkin.directlotapp.crypto

import android.util.Log
import java.security.Key
import java.security.KeyPairGenerator
import javax.crypto.Cipher

class CryptoPouch {

    constructor() {
        create()
    }

    private var publicKey: Key? = null
    private var privateKey: Key? = null

    private fun create(){
        try {
            val kpg = KeyPairGenerator.getInstance("RSA")
            kpg.initialize(1024)
            val kp = kpg.genKeyPair()
            publicKey = kp.getPublic()
            privateKey = kp.getPrivate()
        } catch (e: Exception) {
            Log.e("Crypto", "RSA key pair error")
        }
    }

    fun encode(text: String) : ByteArray?{
        try {
            val c = Cipher.getInstance("RSA")
            c.init(Cipher.ENCRYPT_MODE, privateKey)
            return c.doFinal(text.toByteArray())
        } catch (e: Exception) {
            Log.e("Crypto", "RSA encryption error")
            return null
        }
    }

    fun decode(encodedBytes: ByteArray) : ByteArray?{
        try {
            val c = Cipher.getInstance("RSA")
            c.init(Cipher.DECRYPT_MODE, publicKey)
            return c.doFinal(encodedBytes)
        } catch (e: Exception) {
            Log.e("Crypto", "RSA decryption error")
            return null
        }
    }


}