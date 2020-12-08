package com.nedaluof.todox.todoxdb

import com.nedaluof.todox.db.TodoXDao
import com.nedaluof.todox.utils.MockUtil.mockTodoX
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
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
class TodoXDaoTest : TodoXDatabaseTest() {

    lateinit var dao: TodoXDao

    @Before
    fun initTodoXDao() {
        dao = todoXDatabase.todoXDao()
    }

    @Test
    fun insertTodoXAndCheckIfExist() {
        //insert new mock TodoX
        dao.insertTest(mockTodoX())
        //load all Todox from table
        val todoXFromDB = dao.getTodoxListTest()
        //check /assert that the new `mock` TodoX inserted
        assertThat(todoXFromDB[0].toString(), `is`(mockTodoX().toString()))
    }
}