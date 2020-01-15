package com.kurotkin.directlotapp.domain.entity

import com.google.gson.annotations.SerializedName

data class LotLite(
    @SerializedName(value = "id")
    val id: Long,

    @SerializedName(value = "name")
    val name: String,

    @SerializedName(value = "price")
    val price: String,

    @SerializedName(value = "photoUrl")
    val photoUrl: String,

    @SerializedName(value = "empty")
    val empty: Boolean
)