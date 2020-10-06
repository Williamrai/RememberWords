package com.example.alwaysremeber.functions.subcategorypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alwaysremeber.database.dao.SubCategoryDao
import java.lang.IllegalArgumentException

class SubCatViewModelFactory (private val id : Long, private val dataSource : SubCategoryDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SubCatViewModel::class.java)){
            return SubCatViewModel(id, dataSource) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}