package com.kurotkin.directlotapp.domain.utils

import com.kurotkin.directlotapp.domain.entity.Lot
import com.kurotkin.directlotapp.domain.entity.LotLite
import com.kurotkin.directlotapp.model.net.entity.LotFromServer
import com.kurotkin.directlotapp.model.net.entity.LotLiteFromServer

class ConvertUtil {

    fun convertLotFromServerToLot(serverLot: LotFromServer) : Lot {
        val empty = isEmpty(serverLot.goods, serverLot.goodsSold)
        val picUrls = mutableListOf<String>()
        serverLot.picUrls.split(";").forEach { picUrls.add(it) }
        return Lot(
            serverLot.countryImg,
            serverLot.date,
            serverLot.discount,
            serverLot.goods,
            serverLot.goodsSold,
            serverLot.id,
            serverLot.location,
            serverLot.name,
            serverLot.parseDate,
            serverLot.photoUrl,
            serverLot.price,
            serverLot.url,
            serverLot.userName,
            empty,
            picUrls
        )
    }

    fun convertLotsLiteFromServer(serverLots: List<LotLiteFromServer>) : List<LotLite>{
        val lots = mutableListOf<LotLite>()
        serverLots.forEach {
            lots.add(convertLotLiteServer(it))
        }
        return lots
    }

    fun convertLotLiteServer(lot: LotLiteFromServer) : LotLite {
        return LotLite(
            lot.id,
            lot.name,
            lot.price,
            lot.photoUrl,
            isEmpty(lot.goods, lot.goodsSold)
        )
    }

    fun isEmpty(goods: Int, goodsSold: Int): Boolean{
        return  goodsSold >= goods
    }
}