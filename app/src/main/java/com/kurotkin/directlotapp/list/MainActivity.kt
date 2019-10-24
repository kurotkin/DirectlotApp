package com.kurotkin.directlotapp.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.kurotkin.directlotapp.R
import com.kurotkin.directlotapp.info.InfoActivity
import com.kurotkin.directlotapp.net.DirectlotService
import com.kurotkin.directlotapp.net.entity.LotLite
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(),
    LiteLotRecyclerAdapter.OnClickListener {
    private lateinit var recyclerAdapter: LiteLotRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAdapter()
        coroutine()
    }

    fun setupAdapter() {
        swipe_container.setOnRefreshListener {
            coroutine()
        }
        recyclerAdapter = LiteLotRecyclerAdapter(this)
        lotList.layoutManager = LinearLayoutManager(this)
        lotList.adapter = recyclerAdapter
    }

    fun coroutine() {
        swipe_container.isRefreshing = true
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = DirectlotService()
            val result = async { apiService.getLastLiteLots() }
            val currentResponse = result.await()
            launch(Dispatchers.Main) {
                updateList(currentResponse.await())
            }
        }
    }

    fun updateList(data: List<LotLite>) {
        recyclerAdapter.setData(data)
        swipe_container.isRefreshing = false
    }

    override fun onItemClick(id: Long) {
        val intent = InfoActivity.getInstance(this, id)
        startActivity(intent)
    }

}
