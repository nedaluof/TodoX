package com.nedaluof.todox.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nedaluof.todox.model.TodoX

/**
 * Created by nedaluof on 10/20/2020.
 */
@Database(entities = [TodoX::class], version = 1, exportSchema = false)
abstract class TodoXDatabase : RoomDatabase() {
    abstract fun todoXDao(): TodoXDao
    companion object{
        const val DB_NAME = "todox.db"
    }
}