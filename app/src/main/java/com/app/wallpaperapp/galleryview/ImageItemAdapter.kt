package com.app.wallpaperapp.galleryview


import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.wallpaperapp.R
import com.app.wallpaperapp.recycler.OnClickInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class ImageItemAdapter(val context: Context, private var imageList: MutableList<ImageItem>,
                       private val listener: OnClickInterface
) :

    RecyclerView.Adapter<ImageItemAdapter.ImageHolder>() {
    val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val recyclerViewItem: View = mLayoutInflater.inflate(R.layout.image_card, parent, false)

        recyclerViewItem.setOnClickListener {
          //  v -> handleRecyclerItemClick(parent as RecyclerView, v)

        }
        return ImageHolder(recyclerViewItem)
    }

   private fun handleRecyclerItemClick(recyclerView: RecyclerView, v: View?) {

   }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        var imgItem = imageList.get(position)
        val url = imgItem.hit?.previewURL
        loadUrl(url, holder.imgView)

    }

    fun loadUrl(url: String?, imgView: ImageView) {

        val requestOptions: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(396)
            .dontAnimate()
            .priority(Priority.HIGH)
            .encodeFormat(Bitmap.CompressFormat.PNG)
            .format(DecodeFormat.DEFAULT)

        if (url !== null) {
            Glide.with(context)
                .applyDefaultRequestOptions(requestOptions)
                .load(url)
                .into(imgView)
        } else {
            imgView.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    inner class ImageHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

      var imgView: ImageView

        init{
            imgView = itemView.findViewById(R.id.image_preview)
            itemView.setOnClickListener( View.OnClickListener { listener.recylerviewOnClick(getAdapterPosition())}
            )
        }
    }

}