package com.app.wallpaperapp

import  android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.wallpaperapp.galleryview.ImageItem
import com.app.wallpaperapp.galleryview.ImageItemAdapter
import com.app.wallpaperapp.recycler.OnClickInterface
import com.app.wallpaperapp.model.Hit
import com.app.wallpaperapp.model.MyLiveData
import com.app.wallpaperapp.webapi.PixabayApi
import com.app.wallpaperapp.webapi.Result
import com.app.wallpaperapp.webapi.RetrofitObject
import kotlinx.coroutines.*
import retrofit2.HttpException


class GalleryFragment : Fragment(), OnClickInterface {

    private lateinit var imageView: ImageView
    private lateinit var recyclerView: RecyclerView
    // private lateinit var model: AppModel
    private lateinit var listner: OnClickInterface
    lateinit var justList: MutableList<Hit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_gallery, container, false)
        recyclerView = view.findViewById(R.id.recycler_gallery)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

     //   val mList = MutableList<ImageItem>(0, { ImageItem(null, -1) });
     //   recyclerView.adapter = context?.let { ImageItemAdapter(it, mList, this) }


        val btn = view.findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            val etxt = view.findViewById<EditText>(R.id.editTextTextPersonName)
            val str: String = etxt.text.toString()
            doRequest(str)
        }


/*         model.hits?.observe(viewLifecycleOwner) {
            Log.d("LIVEDATA", "Observer works")
            val list = buildList(it)
            recyclerView.adapter = context?.let { ImageItemAdapter(it, list, this) }
        }*/

        listner = this
        val myModel = ViewModelProvider(requireActivity()).get(MyLiveData::class.java)
        myModel.getDataList().observe(requireActivity()) { customList ->
            val da = customList!!
            Log.d("FRAGMENT PREVIEW ", "Data sent" + da.size.toString())
            val list = buildList(customList)
            recyclerView.adapter = context?.let { ImageItemAdapter(it, list, listner) }
        }


            val category = myModel.currentCategory
            doRequest(category)

        return view
    }


    fun buildList(hits: MutableList<Hit>): MutableList<ImageItem> {

        val dataList = mutableListOf<ImageItem>()
        for (hit in hits) {
            dataList.add(ImageItem(hit, -1))
        }
        return dataList
    }


    fun initRecyclerView(list: MutableList<ImageItem>) {
        val list = buildList(justList)
        recyclerView.adapter = context?.let { ImageItemAdapter(it, list, listner) }
    }


    fun doRequest(keyword: String) {
        val pixabayApi = RetrofitObject.getClient().create(PixabayApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            val response = pixabayApi.getQuotes("37309411-fe5f3d44facc80b63b845c092", "$keyword", "photo")
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        Log.d("PIXABAY: ", response.body().toString())
                        val result: Result = response.body()!!
                        val myModel = ViewModelProvider(requireActivity()).get(MyLiveData::class.java)
                        myModel.dataList?.postValue(result.hits)

                    } else {
                        Log.d(
                            "PIXABAY ERROR: ", response.body().toString() + " " +
                                    response.headers().toString()
                        )
                    }
                } catch (e: HttpException) {
                    Log.d("HTTP_ERROR", "Exception ${e.message}")
                } catch (e: Throwable) {
                    Log.d("HTTP_CRAH", "Ooops: Something else went wrong")
                }
            }
        }

    }


    override fun recylerviewOnClick(position: Int) {

        val myModel = ViewModelProvider(requireActivity()).get(MyLiveData::class.java)
        var hits =  myModel.dataList?.value
        val hit = hits?.get(position)

        if (hit != null) {
            myModel.currentPicture = hit.largeImageURL
        }

        val controller = findNavController()
        controller.navigate(R.id.blankFragment)
    }
}
