package com.example.myimagefinder.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myimagefinder.Retrofit.KakaoImageData

class ImageViewModel : ViewModel() {
    private val _liveDataList = MutableLiveData<List<KakaoImageData>>()
    val liveDataList: LiveData<List<KakaoImageData>> get() = _liveDataList

    fun pickedDataList(dataList: MutableList<KakaoImageData>) {
        val pickedList = dataList.filter { it.isLiked }
        _liveDataList.value = pickedList.toMutableList()
    }

    fun getPickedDataList(): MutableList<KakaoImageData> =
        liveDataList.value?.toMutableList() ?: mutableListOf()
}