package com.nedaluof.todox.todoxdb

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.nedaluof.todox.db.TodoXDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by nedaluof on 11/4/2020.
 */

@RunWith(RobolectricTestRunner::class)
@Config(
    sdk = [27],
    manifest = Config.NONE
)
abstract class TodoXDatabaseTest {

    lateinit var todoXDatabase: TodoXDatabase

    @Before
    fun setupDatabase() {
        todoXDatabase =
            Room.inMemoryDatabaseBuilder(
                getApplicationContext(),
                TodoXDatabase::class.java
            )
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDatabase() {
        todoXDatabase.close()
    }

}