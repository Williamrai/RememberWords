package com.example.alwaysremeber.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "main_category_table")
data class MainCategory(
    @PrimaryKey(autoGenerate = true)
    val main_cat_id : Long = 0L,

    @ColumnInfo(name = "main_cat_name")
    var mainCatName : String
)