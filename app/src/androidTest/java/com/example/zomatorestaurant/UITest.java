package com.example.zomatorestaurant;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class UITest {

    @Rule
    public IntentsTestRule mActivityRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void activityLaunch(){
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.scrollToPosition(6));
    }
}
