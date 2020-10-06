package com.example.alwaysremeber.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words_table")
data class Words (
    @PrimaryKey(autoGenerate = true)
    var wordId : Long = 0L,

    var words : String,

    @ColumnInfo(name = "word_category_id")
    var wordCatId : Long = 0L
)