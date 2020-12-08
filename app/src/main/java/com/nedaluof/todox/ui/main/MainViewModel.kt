package com.nedaluof.todox.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nedaluof.todox.model.TodoX
import com.nedaluof.todox.repo.CentralRepository

/**
 * Created by nedaluof on 10/21/2020.
 */
class MainViewModel @ViewModelInject constructor(
    private val repository: CentralRepository
) : ViewModel() {

    fun getAll() = repository.getAllTodox()
    fun insert(todo: TodoX) = repository.insertTodox(todo)
    fun deleteTodoX(todox: TodoX) = repository.deleteTodox(todox)

    override fun onCleared() {
        super.onCleared()
        repository.clearDisposables()
    }

}