package com.nedaluof.todox.ui.todoxoperations

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nedaluof.todox.model.TodoX
import com.nedaluof.todox.repo.CentralRepository

/**
 * Created by nedaluof on 10/25/2020.
 */
class TodoXOperationsViewModel @ViewModelInject constructor(
    private val repository: CentralRepository
) : ViewModel() {
    fun insertTodoX(todo: TodoX) = repository.insertTodox(todo)
    fun updateTodoX(todo: TodoX) = repository.updateTodox(todo)
}