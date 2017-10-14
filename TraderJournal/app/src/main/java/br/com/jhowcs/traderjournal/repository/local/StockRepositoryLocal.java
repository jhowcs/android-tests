package br.com.jhowcs.traderjournal.repository.local;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.jhowcs.traderjournal.model.StockModel;
import br.com.jhowcs.traderjournal.repository.local.contract.StockContract;

public class StockRepositoryLocal implements DataRepository<StockModel> {

    @Override
    public List<StockModel> getAll() {
        List<StockModel> modelList = new ArrayList<>();

        Cursor cursor = DatabaseProvider.getReadableDatabase()
                .query(StockContract.TABLE_NAME, new String[]{
                                StockContract.COLUMN_NAME,
                                StockContract.COLUMN_ENTRY_PRICE,
                                StockContract.COLUMN_STOP_LOSS},
                        null,
                        null,
                        null,
                        null,
                        null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                modelList.add(
                        new StockModel(cursor.getString(cursor.getColumnIndex(StockContract.COLUMN_NAME)),
                                cursor.getString(cursor.getColumnIndex(StockContract.COLUMN_ENTRY_PRICE)),
                                cursor.getString(cursor.getColumnIndex(StockContract.COLUMN_STOP_LOSS))));
            }
            cursor.close();
        }
        return modelList;
    }

    @Override
    public StockModel getById(String id) {
        String[] whereArgs = new String[] {id};
        String whereClause = StockContract._ID + "= ?";

        Cursor cursor = DatabaseProvider.getReadableDatabase()
                .query(StockContract.TABLE_NAME, new String[]{
                                StockContract._ID,
                                StockContract.COLUMN_NAME,
                                StockContract.COLUMN_ENTRY_PRICE,
                                StockContract.COLUMN_STOP_LOSS},
                        whereClause,
                        whereArgs,
                        null,
                        null,
                        null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                StockModel stockModel = new StockModel(cursor.getString(cursor.getColumnIndex(StockContract.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(StockContract.COLUMN_ENTRY_PRICE)),
                        cursor.getString(cursor.getColumnIndex(StockContract.COLUMN_STOP_LOSS)));
                stockModel.setId(cursor.getString(cursor.getColumnIndex(StockContract._ID)));
                return stockModel;
            }
        }

        return null;
    }

    @Override
    public long insert(StockModel type) {
        ContentValues cv = new ContentValues();
        cv.put(StockContract.COLUMN_NAME, type.getStockName());
        cv.put(StockContract.COLUMN_ENTRY_PRICE, type.getEntryPrice());
        cv.put(StockContract.COLUMN_STOP_LOSS, type.getStopLoss());

        return DatabaseProvider.getReadableDatabase()
                .insert(StockContract.TABLE_NAME, null, cv);
        }

    @Override
    public int delete(String id) {
        String[] whereArgs = new String[] {id};
        String whereClause = StockContract._ID + "= ?";

        return DatabaseProvider.getWritableDatabase()
                .delete(StockContract.TABLE_NAME, whereClause, whereArgs);
    }

    @Override
    public void update(StockModel type) {
        ContentValues cv = new ContentValues();
        cv.put(StockContract.COLUMN_NAME, type.getStockName());
        cv.put(StockContract.COLUMN_ENTRY_PRICE, type.getEntryPrice());
        cv.put(StockContract.COLUMN_STOP_LOSS, type.getStopLoss());

        String[] whereArgs = new String[] {type.getId()};
        String whereClause = StockContract._ID + "= ?";

        DatabaseProvider.getWritableDatabase().update(StockContract.TABLE_NAME,
                cv,
                whereClause, whereArgs);
    }
}
