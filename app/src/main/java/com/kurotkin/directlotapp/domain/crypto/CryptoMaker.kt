package com.kurotkin.directlotapp.domain.crypto

interface CryptoMaker {
    fun encode(text: String) : ByteArray?
    fun decode(encodedBytes: ByteArray) : ByteArray?
    fun decode(encoded: String) : String?
    fun getPublicKey() : String
}