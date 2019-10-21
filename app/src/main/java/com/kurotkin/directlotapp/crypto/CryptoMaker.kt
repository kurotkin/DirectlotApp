package com.kurotkin.directlotapp.crypto

interface CryptoMaker {
    fun encode(text: String) : ByteArray?
    fun decode(encodedBytes: ByteArray) : ByteArray?
    fun getPublicKey() : String
}