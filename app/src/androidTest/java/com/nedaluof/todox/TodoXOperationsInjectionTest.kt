package com.nedaluof.todox

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.nedaluof.todox.ui.todoxoperations.TodoXOperations
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by nedaluof on 11/8/2020.
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TodoXOperationsInjectionTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun test_Injection() {
        with(launchFragment<TodoXOperations>()) {
            onFragment {
                assertThat(it.viewModel).isNotNull()
            }
        }
    }
}