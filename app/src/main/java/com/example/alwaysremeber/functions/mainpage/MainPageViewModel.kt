package com.example.alwaysremeber.functions.mainpage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alwaysremeber.database.dao.MainCategoryDao
import com.example.alwaysremeber.database.tables.MainCategory
import kotlinx.coroutines.*

class MainPageViewModel(private val dataSource: MainCategoryDao) : ViewModel() {


    private val mainViewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + mainViewModelJob)

    val mainCatDatas = dataSource.getAllMainCatData()

    private val _onClickEvent = MutableLiveData<Long?>()
    val onClickEvent : MutableLiveData<Long?>
        get() = _onClickEvent

    fun insert(bookName : String){
        uiScope.launch {
            withContext(Dispatchers.IO){
                val type = MainCategory(mainCatName = bookName)
                dataSource.insert(type)
            }
        }
    }



    fun onClick(mainCategoryId : Long){
        _onClickEvent.value = mainCategoryId
    }

    fun navigationFinished(){
        _onClickEvent.value = null
    }

    fun clear(){
        uiScope.launch {
            withContext(Dispatchers.IO){
                dataSource.clear().apply {
                    dataSource.clearSubCategory()
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        mainViewModelJob.cancel()
    }

}