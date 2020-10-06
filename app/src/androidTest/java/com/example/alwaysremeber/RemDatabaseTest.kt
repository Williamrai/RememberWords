package com.example.alwaysremeber

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.alwaysremeber.database.MainDatabase
import com.example.alwaysremeber.database.dao.MainCategoryDao
import com.example.alwaysremeber.database.dao.WordsDao
import com.example.alwaysremeber.database.tables.MainCategory
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class RemDatabaseTest {

    private lateinit var mainCategoryDao: MainCategoryDao
    private lateinit var wordsDao: WordsDao
    private lateinit var db: MainDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, MainDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        mainCategoryDao = db.mainCategoryDao
        wordsDao = db.wordsDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insert(){
        val type = MainCategory(mainCatName = "words")
        mainCategoryDao.insert(type)
        type.mainCatName = "fruits"
        mainCategoryDao.update(type)


        Assert.assertEquals(type?.mainCatName,"fruits")
    }
}