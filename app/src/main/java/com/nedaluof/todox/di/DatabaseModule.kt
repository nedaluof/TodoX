package com.nedaluof.todox.di

import android.content.Context
import androidx.room.Room
import com.nedaluof.todox.db.TodoXDao
import com.nedaluof.todox.db.TodoXDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by nedaluof on 10/20/2020.
 */

/*
* DatabaseModule First Round , will be changed , my be concise
* to provide Dao directly
* */
@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideTodoXDatabase(@ApplicationContext context: Context): TodoXDatabase =
        Room.databaseBuilder(
            context,
            TodoXDatabase::class.java,
            TodoXDatabase.DB_NAME
        ).build()

    @Singleton
    @Provides
    fun provideTodoXDao(database: TodoXDatabase): TodoXDao = database.todoXDao()

}