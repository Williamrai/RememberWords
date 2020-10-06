package com.example.alwaysremeber.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "sub_category_table"
)
data class SubCategory(
    @PrimaryKey(autoGenerate = true)
    var sub_cat_id: Long = 0L,

    @ColumnInfo(name = "sub_cat-name")
    var subCatName: String ?= null,

    @ColumnInfo(name = "main_ref_cat_id")
    val mainRefCatId : Long = 0L

)