package br.com.jhowcs.traderjournal.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.jhowcs.traderjournal.R;
import br.com.jhowcs.traderjournal.model.StockModel;
import br.com.jhowcs.traderjournal.repository.local.DatabaseProvider;
import br.com.jhowcs.traderjournal.repository.local.StockRepositoryLocal;

import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public static final String EXTRA_STOCK_DATA = "stock_data";

    @Rule
    public ActivityTestRule<MainActivity> rule
            = new ActivityTestRule<>(MainActivity.class, true, false);

    StockRepositoryLocal repositoryLocal;

    @Before
    public void setup() {
        DatabaseProvider.init(InstrumentationRegistry.getTargetContext());
        DatabaseProvider.clearTables();
        repositoryLocal = new StockRepositoryLocal();
    }

    @Test
    public void whenStartActivityWithEmptyData_shouldShowEmptyState() {
        rule.launchActivity(new Intent());
        Espresso.onView(allOf(ViewMatchers.withId(R.id.txtEmptyStateMessage),
                ViewMatchers.withText("nothing to show")))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void whenStartActivityWithData_shouldShowDataInRecyclerViewList() {
        populateDatabase();
        rule.launchActivity(new Intent());
        Espresso.onView(allOf(ViewMatchers.withId(R.id.txtStockName),
                ViewMatchers.withText("USIM5")))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(allOf(ViewMatchers.withId(R.id.txtEntryPrice),
                ViewMatchers.withText("9,14")))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(allOf(ViewMatchers.withId(R.id.txtStopLoss),
                ViewMatchers.withText("8,95")))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void whenClickFabButton_shouldCallIntentSockRegister() {
        Intents.init();
        rule.launchActivity(new Intent());
        Espresso.onView(ViewMatchers.withId(R.id.fab))
                .perform(ViewActions.click());
        prepareIntent();
        Intents.release();
    }

    @Test
    public void whenRegisterANewStock_shouldShowOnRecyclerViewList() {
        Intents.init();
        rule.launchActivity(new Intent());
        prepareIntentResult();
        Espresso.onView(ViewMatchers.withId(R.id.fab))
                .perform(ViewActions.click());
        prepareIntent();
        Intents.release();
    }

    private void prepareIntent() {
        Intents.intended(IntentMatchers.hasComponent(StockRegister.class.getName()));
    }

    private void prepareIntentResult() {
        StockModel stockModel = new StockModel("ELET3", "21,30", "19,99");

        Intent intent = new Intent();
        intent.putExtra(EXTRA_STOCK_DATA,  stockModel);

        Intents.intending(IntentMatchers.hasComponent(StockRegister.class.getName()))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, intent));
    }

    private void populateDatabase() {
        repositoryLocal
                .insert(new StockModel("USIM5", "9,14", "8,95"));

        repositoryLocal
                .insert(new StockModel("PTBL3", "5,14", "5,45"));

        repositoryLocal
                .insert(new StockModel("PETR3", "16,72", "16,00"));

        repositoryLocal
                .insert(new StockModel("ELET3", "21,30", "19,99"));

        repositoryLocal
                .insert(new StockModel("AMAR3", "9,32", "9,00"));

        repositoryLocal
                .insert(new StockModel("GOAU3", "5,08", "4,83"));

        repositoryLocal
                .insert(new StockModel("CMIG3", "8,30", "8,00"));

        repositoryLocal
                .insert(new StockModel("EVEN3", "5,88", "5,68"));
    }
}