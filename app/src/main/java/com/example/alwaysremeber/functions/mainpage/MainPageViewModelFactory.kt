package com.example.alwaysremeber.functions.mainpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alwaysremeber.database.dao.MainCategoryDao
import java.lang.IllegalArgumentException

class MainPageViewModelFactory(private val dataSource: MainCategoryDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainPageViewModel::class.java)) {
            return MainPageViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}