package com.example.alwaysremeber.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.alwaysremeber.database.tables.Words

@Dao
interface WordsDao {

    @Insert
    fun insert(words: Words)

    @Update
    fun update(words: Words)

    @Query("SELECT * from words_table ORDER BY wordId DESC")
    fun getAllWords() : LiveData<List<Words>>

    @Query("SELECT * FROM words_table ORDER BY wordId DESC LIMIT 1")
    fun getLatestWord() : Words?

    @Query("DELETE FROM words_table")
    fun clear()

}