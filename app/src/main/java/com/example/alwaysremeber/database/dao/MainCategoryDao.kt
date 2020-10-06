package com.example.alwaysremeber.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.alwaysremeber.database.relation.MainAndCategory
import com.example.alwaysremeber.database.tables.MainCategory

@Dao
interface MainCategoryDao {

    @Insert
    fun insert(mainCategory : MainCategory)

    @Update
    fun update(mainCategory: MainCategory)

    @Query("SELECT * FROM main_category_table")
    fun getAllMainCatData() : LiveData<List<MainCategory>>

    @Query("DELETE FROM main_category_table")
    fun clear()

    @Query("DELETE FROM sub_category_table")
    fun clearSubCategory()

    @Transaction
    @Query("SELECT * FROM main_category_table")
    fun getTypeAndCategories() : List<MainAndCategory>


}