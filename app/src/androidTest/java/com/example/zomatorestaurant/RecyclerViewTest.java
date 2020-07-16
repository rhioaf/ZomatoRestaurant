package com.example.zomatorestaurant;

import android.view.View;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {
    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void recyclerViewDisplayed() throws InterruptedException{
        Thread.sleep(4000);
        onView(ViewMatchers.withId(R.id.recyclerview)).check(matches(isDisplayed()));
        Thread.sleep(4000);
    }

    @Test
    public void testClickAction() throws InterruptedException {
        Thread.sleep(4000);
        onView(ViewMatchers.withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        Thread.sleep(4000);
    }

    @Test
    public void testScroll() throws InterruptedException{
        Thread.sleep(4000);
        onView(ViewMatchers.withId(R.id.recyclerview)).perform(RecyclerViewActions.scrollToPosition(15));
        Thread.sleep(4000);
    }
}
