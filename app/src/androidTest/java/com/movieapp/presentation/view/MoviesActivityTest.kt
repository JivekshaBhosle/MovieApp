package com.movieapp.presentation.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.movieapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MoviesActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MoviesActivity::class.java)


    @Test
    fun test_isActivityInView() {

        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isMovieListInView() {

        onView(withId(R.id.movie_rv)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isMovieListInViewNotEmpty() {

        onView(withId(R.id.movie_rv)).check(matches(isDisplayed()))

        onView(withId(R.id.error_view_container))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }


}