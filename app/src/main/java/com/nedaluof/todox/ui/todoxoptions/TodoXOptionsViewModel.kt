package com.nedaluof.todox.ui.todoxoptions

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nedaluof.todox.model.TodoX
import com.nedaluof.todox.repo.CentralRepository

/**
 * Created by nedaluof on 11/15/2020.
 */
class TodoXOptionsViewModel @ViewModelInject constructor(
    private val repository: CentralRepository
) : ViewModel() {
    fun deleteTodoX(todox: TodoX) = repository.deleteTodox(todox)
}