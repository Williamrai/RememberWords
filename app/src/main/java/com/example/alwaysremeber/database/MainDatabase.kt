package com.example.alwaysremeber.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.alwaysremeber.database.dao.SubCategoryDao
import com.example.alwaysremeber.database.dao.MainCategoryDao
import com.example.alwaysremeber.database.dao.WordsDao
import com.example.alwaysremeber.database.tables.SubCategory
import com.example.alwaysremeber.database.tables.MainCategory
import com.example.alwaysremeber.database.tables.Words

@Database(entities = [MainCategory::class,SubCategory::class, Words::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {

    /**
     * Connects the database to the DAO.
     */
    abstract val subCategoryDao: SubCategoryDao
    abstract val mainCategoryDao : MainCategoryDao
    abstract val wordsDao: WordsDao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java,
                        "remDb"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}