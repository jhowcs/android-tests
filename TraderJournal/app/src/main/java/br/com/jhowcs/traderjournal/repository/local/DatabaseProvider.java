package br.com.jhowcs.traderjournal.repository.local;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.VisibleForTesting;

import br.com.jhowcs.traderjournal.repository.local.contract.StockContract;

public class DatabaseProvider {
    private static Database database;

    private DatabaseProvider() {}

    public static void init(Context context) {
        database = new Database(context, "trader.db");
    }

    public static SQLiteDatabase getReadableDatabase() {
        return database.getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDatabase() {
        return database.getWritableDatabase();
    }

    @VisibleForTesting
    public static void clearTables() {
        getWritableDatabase().execSQL("DELETE FROM " + StockContract.TABLE_NAME);
    }

    @VisibleForTesting
    public static void initDatabaseInMemory(Context context) {
        database = new Database(context, null);
    }
}
