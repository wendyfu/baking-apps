package io.github.wendyfu.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.wendyfu.bakingapp.recipelist.presentation.RecipeListActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author wendy
 * @since Sep 16, 2017.
 */

@RunWith(AndroidJUnit4.class) public class RecipeListScreenTest {

    private static final String DESSERT_NAME = "Brownies";
    @Rule public ActivityTestRule<RecipeListActivity> activityTestRule =
        new ActivityTestRule<>(RecipeListActivity.class);
    private IdlingResource idlingResource;

    @Before public void registerIdlingResource() {
        idlingResource = activityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test public void clickListItem_OpensDetailActivity() throws InterruptedException {
        onView(withId(R.id.rv_recipe_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withText(DESSERT_NAME)).check(matches(isDisplayed()));
    }

    @After public void unregisterIdlingResources() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }
}
