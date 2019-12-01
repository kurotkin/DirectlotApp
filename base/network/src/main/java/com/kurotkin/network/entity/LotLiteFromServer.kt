package com.kurotkin.directlotapp.model.net.entity

data class LotLiteFromServer(
    val id: Long,
    val name: String,
    val price: String,
    val photoUrl: String,
    val goods: Int,
    val goodsSold: Int
)