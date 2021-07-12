package com.example.clothes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.clothes.ui.activity.ProductListActivity
import com.example.clothes.ui.adapter.ProductsListAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductListActivityTest {
    @get:Rule
    val rule = ActivityTestRule(ProductListActivity::class.java)

    val CLOTHES_NAME_TEST = listOf("pulseira")

    @Test
    fun shouldLoadProductsInRecyclerView(){
        onView(withId(R.id.clothes_recyclerview)).perform(click()).check(matches(isDisplayed()))
    }

//    @Test
//    fun shouldClickInProductCardView(){
//        onView(withId(R.id.clothes_recyclerview)).perform(actionOnItemAtPosition<ProductsListAdapter.ViewHolder>(0, click()))
//
//        onView(withId(R.id.clothes_title)).check(matches(withText(CLOTHES_NAME_TEST)))
//
//        pressBack()
//
//        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
//    }
}