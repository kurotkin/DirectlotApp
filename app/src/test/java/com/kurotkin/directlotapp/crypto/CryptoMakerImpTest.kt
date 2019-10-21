package com.kurotkin.directlotapp.crypto

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CryptoMakerImpTest {

    lateinit var cryptoMaker: CryptoMaker

    @Before
    fun setup() {
        cryptoMaker = CryptoMakerImp()
    }

    @Test
    fun decode() {
        val expected = "PH%Zj3AOB8-ARyPTV7~uMo333M|3sL{8IR%DzCV#lE1ye"

        val encodedBytes: ByteArray? = cryptoMaker.encode(expected)
        val decodedBytes: ByteArray? = cryptoMaker.decode(encodedBytes!!)

        Assert.assertEquals(String(decodedBytes!!), expected)
    }

}