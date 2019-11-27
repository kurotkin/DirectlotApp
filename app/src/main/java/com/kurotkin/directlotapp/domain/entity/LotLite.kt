package com.kurotkin.directlotapp.domain.entity

data class LotLite(
    val id: Long,
    val name: String,
    val price: String,
    val photoUrl: String,
    val empty: Boolean
)