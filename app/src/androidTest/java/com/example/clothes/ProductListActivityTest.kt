package com.example.clothes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.clothes.MockData.FakeProductData
import com.example.clothes.ui.activity.ProductListActivity
import com.example.clothes.ui.adapter.ProductsListAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductListActivityTest {
    @get:Rule
    val rule = ActivityTestRule(ProductListActivity::class.java)
    val productsListMock = FakeProductData.products

    @Test
    fun shouldLoadProductsInRecyclerView_whenHasProducts(){
        onView(withId(R.id.clothes_recyclerview)).perform(click()).check(matches(isDisplayed()))
    }


    //To test pass do you need disable animations on emulator or physical device
    @Test
    fun shouldClickInProductCardView_whenHasProduct(){
        onView(withId(R.id.clothes_recyclerview)).perform(actionOnItemAtPosition<ProductsListAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.name_product)).check(matches(withText(productsListMock[0].name)))
        pressBack()
        onView(withId(R.id.clothes_recyclerview)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldScrollToFindTheProduct_whenHasProductInPosition(){
        onView(withId(R.id.clothes_recyclerview)).perform(RecyclerViewActions.scrollToPosition<ProductsListAdapter.ViewHolder>(21))
        onView(withId(R.id.clothes_recyclerview)).perform(actionOnItemAtPosition<ProductsListAdapter.ViewHolder>(21, click()))
        onView(withText(productsListMock[21].name)).check(matches(isDisplayed()))
    }
}