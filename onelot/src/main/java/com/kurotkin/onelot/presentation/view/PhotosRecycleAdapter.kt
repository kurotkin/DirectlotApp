package com.kurotkin.directlotapp.presentation.one_lot.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kurotkin.onelot.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo.view.*
import java.util.*

class PhotosRecycleAdapter : RecyclerView.Adapter<PhotosRecycleAdapter.ViewHolder>(){

    private var urls: MutableList<String> = LinkedList()

    fun setData(items: List<String>){
        this.urls.clear()
        this.urls.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(urls[position])

    override fun getItemCount(): Int = urls.size

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_photo, parent, false)){

        fun bind(url: String) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_photo_error)
                .fit()
                .centerCrop()
                .into(itemView.imageView)
        }
    }
}