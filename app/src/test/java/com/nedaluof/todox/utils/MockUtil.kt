package com.nedaluof.todox.utils

import com.nedaluof.todox.model.TodoX

/**
 * Created by nedaluof on 11/4/2020.
 */
object MockUtil {

    fun mockTodoxList(numbers: Int): List<TodoX> {
        val mockList = ArrayList<TodoX>()
        for (i in 0.rangeTo(numbers)) {
            mockList.add(
                TodoX(
                    title = "Title $i",
                    description = "Description $i"
                )
            )
        }
        return mockList
    }

    fun mockTodoX() = TodoX(
        title = "New title 🎁",
        description = "New description 👽 ",
        createdAt = "2020"
    )
}