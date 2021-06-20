package com.example.mandiritest

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mandiritest.ui.main.MainActivity
import com.example.mandiritest.ui.source.SourceListActivity
import com.example.mandiritest.util.RecyclerViewMatcher
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
class MainActivityTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1)
    var rule = ActivityScenarioRule(MainActivity::class.java)
    @Before
    fun init() {
        hiltRule.inject()
        Intents.init()
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mandiritest", appContext.packageName)
        Espresso.onView(withId(R.id.title_txt)).check(ViewAssertions.matches(ViewMatchers.withText("Select Category")))
        val rv = Espresso.onView(withId(R.id.rv))
        Assert.assertNotNull(rv)
        Espresso.onView(
            RecyclerViewMatcher(R.id.rv)
                .atPositionOnView(0, R.id.category_name)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(Constant.CATEGORIES[0].capitalize())))
            .perform(ViewActions.click())
        Intents.intended(hasComponent(SourceListActivity::class.java.name))
    }
}
