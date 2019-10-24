package com.kurotkin.directlotapp.net.entity

data class Lot(
    val countryImg: String,
    val date: String,
    val discount: String,
    val goods: Int,
    val goodsSold: Int,
    val id: Long,
    val location: String,
    val name: String,
    val parseDate: String,
    val photoUrl: String,
    val price: String,
    val url: String,
    val userName: String
)