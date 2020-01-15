package com.kurotkin.directlotapp.model.net.entity

import com.google.gson.annotations.SerializedName

data class LotLiteFromServer(
    @SerializedName(value = "id")
    val id: Long,

    @SerializedName(value = "name")
    val name: String,

    @SerializedName(value = "price")
    val price: String,

    @SerializedName(value = "photoUrl")
    val photoUrl: String,

    @SerializedName(value = "goods")
    val goods: Int,

    @SerializedName(value = "goodsSold")
    val goodsSold: Int
)