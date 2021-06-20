package com.example.mandiritest

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.mandiritest.ui.source.SourceListActivity
import com.example.mandiritest.util.RecyclerViewMatcher
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


//@RunWith(AndroidJUnit4::class)
//@LargeTest
@HiltAndroidTest
class SourceListActivityTest {

    @get:Rule()
    var hiltRule = HiltAndroidRule(this)
    lateinit var activityScenario: ActivityScenario<SourceListActivity>
//    var mActivityRule: ActivityTestRule<SourceListActivity> =
//        ActivityTestRule(SourceListActivity::class.java, false, false)

    @Before
    fun init() {
        val intent = SourceListActivity.createIntent(ApplicationProvider.getApplicationContext(), "Business")
        activityScenario = ActivityScenario.launch(intent)
        activityScenario.onActivity {
            //whatever you like
        }
        hiltRule.inject()
        Intents.init()
    }

    @Test
    fun listStrings() {
        onView(withId(R.id.title_txt)).check(matches(withText("Select Source")))
        val rv = onView(withId(R.id.rv))
        Assert.assertNotNull(rv)
//        onView(
//            RecyclerViewMatcher(R.id.rv)
//                .atPositionOnView(0, R.id.category_name)
//        )
//            .check(matches(withText("Business")))
//            .perform(click())
//        intended(hasComponent(SourceListActivity::class.java.name))
//        rv.perform(RecyclerViewActions.actionOnItem(hasDescendant(withText("business")), isDisplayed()))
//        onView(withId(R.id.rv))
//            .perform(
//                RecyclerViewActions
//                .actionOnItemAtPosition<MyAdapter.ViewHolder>(1,clickItemWithId(R.id.button)))
//        onView(withText("business")).check(matches(isDisplayed()))
//        onView(withText("general")).check(matches(isDisplayed()))
//        onView(withText("health")).check(matches(isDisplayed()))
//        onView(withText("science")).check(matches(isDisplayed()))
//        onView(withText("sports")).check(matches(isDisplayed()))
//        onView(withText("technology")).check(matches(isDisplayed()))
    }
}