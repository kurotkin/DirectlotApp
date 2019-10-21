package com.kurotkin.directlotapp.crypto

import android.util.Log
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.security.Key
import java.security.KeyPairGenerator
import java.util.*
import javax.crypto.Cipher


class CryptoMakerImp : CryptoMaker{

    constructor() {
        genKeys()
    }

    constructor(publicKey: Key?) {
        this.publicKey = publicKey
    }

    private var publicKey: Key? = null
    private var privateKey: Key? = null

    private fun genKeys(){
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

    override fun encode(text: String) : ByteArray?{
        try {
            val c = Cipher.getInstance("RSA")
            c.init(Cipher.ENCRYPT_MODE, privateKey)

            return c.doFinal(text.toByteArray())
        } catch (e: Exception) {
            Log.e("Crypto", "RSA encryption error")
            return null
        }
    }

    override fun decode(encodedBytes: ByteArray) : ByteArray?{
        try {
            val c = Cipher.getInstance("RSA")
            c.init(Cipher.DECRYPT_MODE, publicKey)
            return c.doFinal(encodedBytes)
        } catch (e: Exception) {
            Log.e("Crypto", "RSA decryption error")
            return null
        }
    }

    override fun getPublicKey() : String{
        if(publicKey == null) return ""
        return publicKey!!.toStringVal()
    }

    private fun Key.toStringVal() : String{
        val baos = ByteArrayOutputStream()
        val oos = ObjectOutputStream(baos)
        oos.writeObject(this)
        oos.close()
        return android.util.Base64.encodeToString(baos.toByteArray(), android.util.Base64.DEFAULT)
    }

    private fun String.toKey() : Key{
        // https://stackoverflow.com/questions/134492/how-to-serialize-an-object-into-a-string
        val data = android.util.Base64.decode().getDecoder().decode(s)
        val ois = ObjectInputStream(ByteArrayInputStream(data))
        val o = ois.readObject()
        ois.close()
        return o
    }

}