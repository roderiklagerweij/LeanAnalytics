package com.rl.leananalytics

import android.app.Application
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.rl.leananalytics.ui.FragmentActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class FragmentTest {

    @Rule
    @JvmField
    public val rule = ActivityTestRule(FragmentActivity::class.java, false, false)

    val mockTrackPageAdapter = mock(TrackingAdapter::class.java)

    @Before
    fun setup() {
        LeanAnalyticsSdk.init(InstrumentationRegistry.getTargetContext().applicationContext as Application, mockTrackPageAdapter)
    }

    @Test
    fun testTrackPage() {
        LeanAnalyticsSdk.setTrackPageConfiguration(TrackPageConfiguration.TRACK_ONCREATE)
        rule.launchActivity(Intent())
        onView(withText(R.string.fragment_button)).check(matches(isDisplayed()))

        verify(mockTrackPageAdapter, times(1)).trackPage("com.rl.leananalytics.ui.FragmentActivity")
    }

    @Test
    fun testTrackClick() {
        LeanAnalyticsSdk.setTrackPageConfiguration(TrackPageConfiguration.TRACK_ONCREATE)
        rule.launchActivity(Intent())
        onView(withText(R.string.fragment_button)).check(matches(isDisplayed())).perform(click())

        verify(mockTrackPageAdapter, times(1)).trackAction("fragment_button")
    }
}
