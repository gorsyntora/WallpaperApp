package com.app.wallpaperapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.wallpaperapp.model.Hit
import com.app.wallpaperapp.recycler.Item

class MyLiveData : ViewModel() {

    var dataList: MutableLiveData<MutableList<Hit>>? = null
    var currentPicture: String = ""
    var currentCategory: String = "Summer"
    lateinit var categoryList: List<Item>

    internal fun getDataList(): MutableLiveData<MutableList<Hit>> {
        if (dataList == null) {
            dataList = MutableLiveData()
        }
        return dataList as MutableLiveData<MutableList<Hit>>
    }

    fun setCategory(pos: Int) {
        currentCategory = categoryList[pos].caption
    }

}