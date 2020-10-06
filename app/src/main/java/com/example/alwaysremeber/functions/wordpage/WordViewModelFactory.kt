package com.example.alwaysremeber.functions.wordpage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alwaysremeber.database.dao.WordsDao
import java.lang.IllegalArgumentException

class WordViewModelFactory(
    private var dataSource : WordsDao,
    private var application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(WordViewModel::class.java)){
            return WordViewModel(dataSource,application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }


}