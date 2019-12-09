package com.kurotkin.directlotapp.domain.utils

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ConvertUtilTest {
    val convertUtil = ConvertUtil()

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
    }

    @Test
    fun convertLotFromServerToLot() {
    }

    @Test
    fun convertLotsLiteFromServer() {
    }

    @Test
    fun convertLotLiteServer() {
    }

    @Test
    fun isEmpty() {
        val actual = convertUtil.isEmpty(34, 34)
        assertEquals(true, actual)
    }
}