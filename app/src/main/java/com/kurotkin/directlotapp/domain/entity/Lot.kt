package com.kurotkin.directlotapp.domain.entity

import com.google.gson.annotations.SerializedName

data class Lot(
    @SerializedName(value = "countryImg")
    val countryImg: String,

    @SerializedName(value = "date")
    val date: String,

    @SerializedName(value = "discount")
    val discount: String,

    @SerializedName(value = "goods")
    val goods: Int,

    @SerializedName(value = "goodsSold")
    val goodsSold: Int,

    @SerializedName(value = "id")
    val id: Long,

    @SerializedName(value = "location")
    val location: String,

    @SerializedName(value = "name")
    val name: String,

    @SerializedName(value = "parseDate")
    val parseDate: String,

    @SerializedName(value = "photoUrl")
    val photoUrl: String,

    @SerializedName(value = "price")
    val price: String,

    @SerializedName(value = "url")
    val url: String,

    @SerializedName(value = "userName")
    val userName: String,

    @SerializedName(value = "empty")
    val empty: Boolean,

    @SerializedName(value = "picUrls")
    val picUrls: List<String>
)