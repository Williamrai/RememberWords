package com.example.alwaysremeber.functions.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    val bookName = MutableLiveData<String>()

    fun sendBookName( text : String){
        bookName.value = text
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("test","onCleared SearchViewModel")
    }
}