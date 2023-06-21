package com.app.wallpaperapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.wallpaperapp.recycler.OnClickInterface
import com.app.wallpaperapp.model.MyLiveData
import com.app.wallpaperapp.recycler.Item
import com.app.wallpaperapp.recycler.ItemViewAdapter

class StartFragment : Fragment(), OnClickInterface {

    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_start, container, false)
        recyclerView = view.findViewById(R.id.content_view)
        val myModel = ViewModelProvider(requireActivity()).get(MyLiveData::class.java)
        myModel.categoryList = buildList()
        setRecyclerView(myModel.categoryList)

         return view
    }

    private fun buildList(): List<Item> {

        return listOf(
            Item("Summer", R.drawable.summer),
            Item("Xmas", R.drawable.xmas),
            Item("Fruits", R.drawable.fruits),
            Item("Motorbikes", R.drawable.motorbikes),
            Item("Yachts", R.drawable.yachts),
        )

    }

    fun setRecyclerView(list: List<Item?>?) {

        recyclerView.setHasFixedSize(false)
        val adapter = context?.let { ItemViewAdapter(list, it, this) }
        recyclerView.setAdapter(adapter)

    }

    override fun recylerviewOnClick(position: Int) {
        val myModel = ViewModelProvider(requireActivity()).get(MyLiveData::class.java)
        myModel.setCategory(position)
        Log.d("StartAPP", "APP")
        val controller = findNavController()
        controller.navigate(R.id.galleryFragment)
    }

}