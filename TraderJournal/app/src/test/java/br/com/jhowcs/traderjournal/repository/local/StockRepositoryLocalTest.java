package br.com.jhowcs.traderjournal.repository.local;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import br.com.jhowcs.traderjournal.BuildConfig;
import br.com.jhowcs.traderjournal.model.StockModel;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class StockRepositoryLocalTest {

    DataRepository<StockModel> repositoryLocal;

    @Before
    public void setup() {
        DatabaseProvider.initDatabaseInMemory(RuntimeEnvironment.application);
        repositoryLocal = new StockRepositoryLocal();
    }

    @Test
    public void whenCallInsertMethod_shouldSaveDataInTheDatabase() {
        repositoryLocal
                .insert(new StockModel("USIM5", "9,14", "8,95"));

        List<StockModel> repositoryLocalAll = repositoryLocal.getAll();
        Assert.assertEquals(1, repositoryLocalAll.size());
    }

    @Test
    public void whenInsertANewStock_shouldReturnTheStockIdSaved() {
        long id = repositoryLocal
                .insert(new StockModel("MOVI3", "8,68", "8,05"));

        Assert.assertEquals(1L, id);
    }

    @Test
    public void whenDeleteAStock_shouldReturnTheNumberOfLinesAffected() {
        repositoryLocal
                .insert(new StockModel("USIM5", "9,14", "8,95"));
        long deleteId = repositoryLocal
                .insert(new StockModel("PTBL3", "5,14", "4,95"));


        int rowsAffected = repositoryLocal.delete(String.valueOf(deleteId));

        Assert.assertEquals(1, rowsAffected);
    }

    @Test
    public void whenCallUpdate_shouldUpdateAStockStopLoss() {
        StockModel stock = new StockModel("USIM5", "9,14", "8,95");
        long idInserted = repositoryLocal.insert(stock);

        stock.setId(String.valueOf(idInserted));
        stock.setStopLoss("9,20");
        repositoryLocal.update(stock);

        StockModel stockUpdated = repositoryLocal.getById(String.valueOf(idInserted));
        Assert.assertEquals("9,20", stockUpdated.getStopLoss());
    }


    @After
    public void tearDown() {
        DatabaseProvider.clearTables();
    }
}