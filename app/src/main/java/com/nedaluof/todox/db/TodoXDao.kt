package com.nedaluof.todox.db

import androidx.room.*
import com.nedaluof.todox.model.TodoX
import io.reactivex.Completable
import io.reactivex.Observable


/**
 * Created by nedaluof on 10/20/2020.
 */
/*
* DAO First Round ,with Rx need to be coroutines will be changed as new features depend
* */
@Dao
interface TodoXDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todox: TodoX): Completable

    @Query("SELECT * FROM todox ORDER BY id DESC")
    fun getAllTodox(): Observable<List<TodoX>>

    @Delete
    fun deleteTodox(todox: TodoX): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTodoX(todox: TodoX): Completable


    //test only

    @Query("delete from todox")
    fun deleteAll(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTest(todox: TodoX)

    //Test usage
    @Query("SELECT * FROM todox ORDER BY id DESC")
    fun getTodoxListTest(): List<TodoX>
}