package com.example.mandiritest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.MockitoAnnotations
import org.mockito.internal.util.reflection.FieldSetter

@ExtendWith(InstantExecutorExtension::class)
abstract class BaseUnitTest {
    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    open fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @AfterEach
    @ExperimentalCoroutinesApi
    open fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    internal fun setFieldValue(systemUnderTest: ViewModel, fieldName: String, fieldValue: Any) {
        FieldSetter.setField(systemUnderTest, systemUnderTest.javaClass.getDeclaredField(fieldName), fieldValue)
    }
}
