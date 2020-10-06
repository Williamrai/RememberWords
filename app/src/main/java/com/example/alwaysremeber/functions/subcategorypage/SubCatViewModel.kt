package com.example.alwaysremeber.functions.subcategorypage

import android.util.Log
import androidx.lifecycle.*
import com.example.alwaysremeber.database.dao.SubCategoryDao
import com.example.alwaysremeber.database.tables.SubCategory
import kotlinx.coroutines.*

class SubCatViewModel(val id : Long, dataSource: SubCategoryDao) : ViewModel() {

    private var catViewModeljob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + catViewModeljob)

    private val database = dataSource

    val categoriesList = database.getAllSubCategories(id)


    private val _successfullInsertion = MutableLiveData<Boolean?>()
    val successfullInsertion: LiveData<Boolean?>
        get() = _successfullInsertion

    private val _watchForClickListener = MutableLiveData<String?>()
    val watchForClickListener : LiveData<String?>
        get() = _watchForClickListener


    fun onSearch(id: Long, bookName: String) {
        uiScope.launch {
            insert(id, bookName)
            _successfullInsertion.postValue(true)
            updateWord(bookName)
        }
    }


    private suspend fun insert(id: Long, bookName: String) {
        withContext(Dispatchers.IO) {
            val categories = SubCategory(subCatName = bookName, mainRefCatId = id)
            val okay = database.insert(categories)

        }
    }


    fun clear() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.clear()
            }
        }

    }

    fun doneInsertion() {
        _successfullInsertion.value = false
    }


    fun updateWord(word : String){
        _watchForClickListener.postValue(word)
    }

    fun doneWatchingForClickListener(){
        _watchForClickListener.value = null
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("test", "onCleared CatViewModel")
        catViewModeljob.cancel()
    }
}