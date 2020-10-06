package com.example.alwaysremeber.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.alwaysremeber.database.tables.SubCategory

@Dao
interface SubCategoryDao {

    @Insert
    fun insert(subCategory:SubCategory) : Long

    @Update
    fun update(subCategory: SubCategory)

    @Query("SELECT * FROM sub_category_table ORDER BY sub_cat_id DESC")
    fun getAllCategories() : LiveData<List<SubCategory>>

    @Query("SELECT * FROM sub_category_table ORDER BY sub_cat_id DESC LIMIT 1")
    fun get() : SubCategory?

    @Query("SELECT * FROM sub_category_table WHERE sub_cat_id = :key")
    fun getCat(key : Long) : SubCategory?

    @Query("DELETE FROM sub_category_table")
    fun clear()

    @Query("SELECT * FROM sub_category_table where main_ref_cat_id = :id ")
    fun getAllSubCategories(id : Long) : LiveData<List<SubCategory>>
}