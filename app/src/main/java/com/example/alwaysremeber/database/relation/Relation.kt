package com.example.alwaysremeber.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.alwaysremeber.database.tables.SubCategory
import com.example.alwaysremeber.database.tables.MainCategory
import com.example.alwaysremeber.database.tables.Words

data class MainAndCategory(
    @Embedded val mainCategory : MainCategory,
    @Relation(
        parentColumn = "main_cat_id",
        entityColumn = "main_ref_cat_id"
    )
    val subCategory: SubCategory
)

data class CategoryAndWords(
    @Embedded val subCategory: SubCategory,
    @Relation(
        parentColumn = "cat_id",
        entityColumn = "word_category_id"
    )
    val words: Words
)