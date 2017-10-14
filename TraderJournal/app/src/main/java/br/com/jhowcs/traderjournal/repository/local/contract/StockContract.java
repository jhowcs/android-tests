package br.com.jhowcs.traderjournal.repository.local.contract;

import android.provider.BaseColumns;

public class StockContract implements BaseColumns {
    public static final String TABLE_NAME = "stock";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ENTRY_PRICE = "entry_price";
    public static final String COLUMN_STOP_LOSS = "stop_loss";
}
