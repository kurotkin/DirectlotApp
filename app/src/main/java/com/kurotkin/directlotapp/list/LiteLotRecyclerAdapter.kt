package com.kurotkin.directlotapp.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kurotkin.directlotapp.R
import com.kurotkin.directlotapp.net.entity.LotLite
import kotlinx.android.synthetic.main.item_lot.view.*
import java.util.*

class LiteLotRecyclerAdapter(private val listener: OnClickListener) : RecyclerView.Adapter<LiteLotRecyclerAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onItemClick(id: Long)
    }

    private var items: MutableList<LotLite> = LinkedList()

    fun setData(items: List<LotLite>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_lot, parent, false)){

        fun bind(lot: LotLite, listener: OnClickListener) {
            itemView.textName.text = lot.name
            itemView.textPrice.text = "${lot.price} \u20BD"
            itemView.setOnClickListener{listener.onItemClick(lot.id)}
        }
    }
}

