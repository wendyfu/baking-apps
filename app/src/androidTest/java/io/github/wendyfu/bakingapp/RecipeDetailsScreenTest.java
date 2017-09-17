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
 * @since Sep 17, 2017.
 */

@RunWith(AndroidJUnit4.class) public class RecipeDetailsScreenTest {

    private static final String STEP_DESCRIPTION = "Recipe Introduction";
    private static final String STEP_NAV_NEXT = "Step 2 of 7";
    private static final String STEP_NAV_PREV = "Step 1 of 7";

    @Rule public ActivityTestRule<RecipeListActivity> activityTestRule =
        new ActivityTestRule<>(RecipeListActivity.class);

    private IdlingResource idlingResource;

    @Before public void registerIdlingResource() {
        idlingResource = activityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);

        openDessertNutellaPie();
        clickOnStep();
    }

    @Test public void testClickStep_ShouldShowStepDetails() throws InterruptedException {
        onView(withText(STEP_DESCRIPTION)).check(matches(isDisplayed()));
    }

    @Test public void testClickButtonNav_ShouldChangeCorrectly() {
        onView(withId(R.id.img_next_step)).perform(click());
        onView(withText(STEP_NAV_NEXT)).check(matches(isDisplayed()));

        onView(withId(R.id.img_prev_step)).perform(click());
        onView(withText(STEP_NAV_PREV)).check(matches(isDisplayed()));
    }

    private void openDessertNutellaPie() {
        onView(withId(R.id.rv_recipe_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    private void clickOnStep() {
        onView(withId(R.id.rv_recipe_steps)).perform(
            RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @After public void unregisterIdlingResources() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }
}
