package com.app.wallpaperapp.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.wallpaperapp.R

class ItemViewAdapter(private val list: List<Item?>?, context: Context, var listener: OnClickInterface) :

    RecyclerView.Adapter<ItemViewAdapter.ViewHolder>() {
    private val mLayoutInflater: LayoutInflater

    init {
        mLayoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val recyclerViewItem = mLayoutInflater.inflate(R.layout.items_layout, parent, false)
        recyclerViewItem.setOnClickListener { v -> handleRecyclerItemClick(parent as RecyclerView, v) }
        return ViewHolder(recyclerViewItem)
    }

    private fun handleRecyclerItemClick(recyclerView: RecyclerView, itemView: View) {
        val itemPosition = recyclerView.getChildLayoutPosition(itemView)
        val item = list?.get(itemPosition)
     }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list?.get(position)
        holder.txtView.text = item?.caption
        if (item != null) {
            holder.imgView.setImageResource(item.drawable)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       var imgView: ImageView
       var txtView: TextView

        init {
            imgView = itemView.findViewById(R.id.imageView)
            txtView = itemView.findViewById(R.id.textView)
            itemView.setOnClickListener( View.OnClickListener { listener.recylerviewOnClick(getAdapterPosition()) }
            )
        }

    }
}
