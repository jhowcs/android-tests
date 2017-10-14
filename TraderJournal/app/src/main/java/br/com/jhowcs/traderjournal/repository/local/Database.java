package br.com.jhowcs.traderjournal.repository.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.jhowcs.traderjournal.repository.local.contract.StockContract;

public class Database extends SQLiteOpenHelper {

    private static final int VERSION = 2;

    public Database(Context context, String name) {
        super(context, name, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ")
                .append(StockContract.TABLE_NAME).append(" ( ")
                .append(StockContract._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ")
                .append(StockContract.COLUMN_NAME).append(" TEXT NOT NULL, ")
                .append(StockContract.COLUMN_ENTRY_PRICE).append(" TEXT NOT NULL, ")
                .append(StockContract.COLUMN_STOP_LOSS).append(" TEXT NOT NULL")
                .append(") ");

        sqLiteDatabase.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // do nothing
    }
}
