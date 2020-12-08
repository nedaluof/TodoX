package com.nedaluof.todox.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nedaluof.todox.db.TodoXDao
import com.nedaluof.todox.model.TodoX
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by nedaluof on 10/21/2020.
 */
@Singleton
class CentralRepository @Inject constructor(
    private val todoXDao: TodoXDao
) {
    private val compositeDisposable = CompositeDisposable()
    fun getAllTodox(): LiveData<List<TodoX>> {
        val todoxLiveData = MutableLiveData<List<TodoX>>()
        compositeDisposable.add(
            todoXDao.getAllTodox()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    todoxLiveData.value = it
                }, {
                    todoxLiveData.value = null
                    Log.d(TAG, "getAllTodox: Error :${it.message} ")
                })
        )
        return todoxLiveData
    }

    fun insertTodox(todo: TodoX): LiveData<Boolean> {
        val resultOfAdd = MutableLiveData<Boolean>()
        compositeDisposable.add(
            todoXDao.insert(todo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ resultOfAdd.value = true }, {
                    resultOfAdd.value = false
                    Log.d(TAG, "Insert Todox: Error ${it.message} ")
                })
        )
        return resultOfAdd
    }

    fun updateTodox(todo: TodoX): LiveData<Boolean> {
        val resultOfUpdate = MutableLiveData<Boolean>()
        compositeDisposable.add(
            todoXDao.updateTodoX(todo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ resultOfUpdate.value = true }, {
                    resultOfUpdate.value = false
                    Log.d(TAG, "Update Todox: Error ${it.message} ")
                })
        )
        return resultOfUpdate
    }


    fun deleteTodox(todo: TodoX): LiveData<Boolean> {
        val resultOfDelete = MutableLiveData<Boolean>()
        compositeDisposable.add(
            todoXDao.deleteTodox(todo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ resultOfDelete.value = true }, {
                    resultOfDelete.value = false
                    Log.d(TAG, "Delete Todox: Error ${it.message} ")
                })
        )
        return resultOfDelete
    }

    fun clearDisposables() = compositeDisposable.clear()


    companion object CentralRepository {
        const val TAG = "MainRepo"
    }
}