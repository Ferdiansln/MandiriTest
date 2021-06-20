package com.example.mandiritest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mandiritest.ui.source.SourceListActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class SourceListActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var rule = ActivityScenarioRule(SourceListActivity::class.java)

    @Before
    fun init() {
        hiltRule.inject()
        Intents.init()
    }

    @Test
    fun listStrings() {
        onView(withId(R.id.title_txt)).check(matches(withText("Select Source")))
        val rv = onView(withId(R.id.rv))
        Assert.assertNotNull(rv)
    }
}