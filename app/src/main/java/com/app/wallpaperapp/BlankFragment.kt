package com.app.wallpaperapp


import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.wallpaperapp.model.MyLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.io.IOException


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BlankFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_blank, container, false)
        val model = ViewModelProvider(requireActivity()).get(MyLiveData::class.java)
        val img = v.findViewById<ImageView>(R.id.imageView3)
        loadUrl(model.currentPicture, img)
        val buttonSetWallpaper = v.findViewById(R.id.imageButton) as ImageButton


        buttonSetWallpaper.setOnClickListener {

            val myWallpaperManager = WallpaperManager.getInstance(context)
            try {
                val bitmap = img.drawable.toBitmap(512, 512)
                myWallpaperManager.setBitmap(bitmap)
            } catch (e: IOException) {

                e.printStackTrace()
            }
        }

        return v
    }

    fun loadUrl(url: String?, imgView: ImageView) {

        val requestOptions: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .override(820)
            .dontAnimate()
            .dontTransform()
            .priority(Priority.HIGH)
            .encodeFormat(Bitmap.CompressFormat.PNG)
            .format(DecodeFormat.DEFAULT)

        if (url !== null) {
            Glide.with(requireContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(url)
                .into(imgView)
        } else {
            imgView.setImageResource(R.drawable.ic_launcher_background)
        }
    }

}