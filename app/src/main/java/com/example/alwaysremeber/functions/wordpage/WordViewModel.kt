package com.example.alwaysremeber.functions.wordpage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.alwaysremeber.database.dao.WordsDao
import com.example.alwaysremeber.database.tables.Words
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class WordViewModel(
    dataSource : WordsDao,
    application: Application) : AndroidViewModel(application) {

    private val databse = dataSource

    private var wordViewModelJob = Job()
    private var uiScoe = CoroutineScope(Dispatchers.Main + wordViewModelJob)

    private var words = MutableLiveData<Words>()

    init {

    }


    override fun onCleared() {
        super.onCleared()
        wordViewModelJob.cancel()

    }


}