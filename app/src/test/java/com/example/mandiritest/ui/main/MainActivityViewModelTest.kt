package com.example.mandiritest.ui.main

import androidx.lifecycle.Observer
import com.example.mandiritest.BaseUnitTest
import com.example.mandiritest.Constant
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor

internal class MainActivityViewModelTest : BaseUnitTest() {
    private lateinit var systemUnderTest: MainViewModel
    private val observer: Observer<MainViewModel.ViewState> = mock()
    @Captor
    internal var captor: ArgumentCaptor<MainViewModel.ViewState>? = null

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @BeforeEach
    override fun setup() {
        super.setup()
        systemUnderTest = MainViewModel()
        systemUnderTest.viewState.observeForever(observer)
    }

    @ExperimentalCoroutinesApi
    @Test
    internal fun onCreate() =
        testCoroutineDispatcher.runBlockingTest {
            captor?.run {
                verify(observer, times(1)).onChanged(capture())
                assertEquals(allValues[0], MainViewModel.ViewState(categories = Constant.CATEGORIES))
            }
        }

}
