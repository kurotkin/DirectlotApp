package com.kurotkin.directlotapp.domain.crypto

import CryptoMaker
import android.util.Base64
import android.util.Log
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.security.Key
import java.security.KeyPairGenerator
import javax.crypto.Cipher


class CryptoMakerImp : CryptoMaker{

    private val className = "CryptoMakerImp"

    private var publicKey: Key? = null
    private var privateKey: Key? = null

    constructor() {
        genKeys()
    }

    constructor(publicKey: String?) {
        this.publicKey = publicKey?.toKey()
    }

    private fun genKeys(){
        try {
            val kpg = KeyPairGenerator.getInstance("RSA")
            kpg.initialize(1024)
            val kp = kpg.genKeyPair()
            publicKey = kp.public
            privateKey = kp.private
        } catch (e: Exception) {
            Log.e(className, "RSA key pair error")
        }
    }

    override fun encode(text: String) : ByteArray?{
        try {
            val c = Cipher.getInstance("RSA")
            c.init(Cipher.ENCRYPT_MODE, privateKey)
            return c.doFinal(text.toByteArray())
        } catch (e: Exception) {
            Log.e(className, "RSA encryption error")
            return null
        }
    }

    override fun decode(encodedBytes: ByteArray) : ByteArray?{
        try {
            val c = Cipher.getInstance("RSA")
            c.init(Cipher.DECRYPT_MODE, publicKey)
            return c.doFinal(encodedBytes)
        } catch (e: Exception) {
            Log.e(className, "RSA decryption error")
            return null
        }
    }

    override fun decode(encoded: String) : String?{
        try {
            val c = Cipher.getInstance("RSA")
            c.init(Cipher.DECRYPT_MODE, publicKey)
            val encodedBytes = Base64.decode(encoded, Base64.DEFAULT)
                //encoded.toByteArray()
            val decoded = c.doFinal(encodedBytes)
            return String(decoded)
        } catch (e: Exception) {
            Log.e(className, "RSA decryption error")
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
        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
    }

    private fun String.toKey() : Key{
        val data = Base64.decode(this, Base64.DEFAULT)
        val ois = ObjectInputStream(ByteArrayInputStream(data))
        val o = ois.readObject() as Key
        ois.close()
        return o
    }

}