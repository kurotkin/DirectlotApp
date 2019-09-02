package com.kurotkin.directlotapp.net

import com.kurotkin.directlotapp.net.entity.Lot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface OnLotCallback { fun onLotCallback(data: List<Lot>?) }

class DirectlotRepo {

    fun getLots(callback: OnLotCallback){
        val apiService = DirectlotService()
        GlobalScope.launch(Dispatchers.Default){
            val currentResponse = apiService.getLastLots().await()
            callback.onLotCallback(currentResponse)
        }
    }

}
