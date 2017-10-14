package br.com.jhowcs.traderjournal.activity;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.jhowcs.traderjournal.R;
import br.com.jhowcs.traderjournal.repository.local.DatabaseProvider;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class StockRegisterTest {
    @Rule
    public ActivityTestRule<StockRegister> rule =
            new ActivityTestRule<>(StockRegister.class, true, false);

    @After
    public void tearDown() {
        DatabaseProvider.clearTables();
    }

    @Test
    public void whenClickOnSaveButtonWithoutFillAllTheFields_shouldShowStatusView() {
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnSave)).perform(click());
        onView(withText("please fill all the fields")).check(matches(isDisplayed()));
    }

    @Test
    public void whenFillAllTheFieldsAndClickOnSaveButton_shouldFinishActivity() {
        rule.launchActivity(new Intent());
        onView(withId(R.id.edtStockName)).perform(typeText("GOAU4"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.edtEntryPrice)).perform(typeText("5.15"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.edtStopLoss)).perform(typeText("4.70"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnSave)).perform(click());

        Assert.assertEquals(true, rule.getActivity().isFinishing());
    }
}