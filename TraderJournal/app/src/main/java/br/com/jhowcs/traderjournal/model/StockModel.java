package br.com.jhowcs.traderjournal.model;

import android.os.Parcel;
import android.os.Parcelable;

public class StockModel implements Parcelable {

    private String id;
    private String stockName;
    private String entryPrice;
    private String stopLoss;

    private static final Parcelable.Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new StockModel(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new Object[0];
        }
    };

    public StockModel() {
    }

    public StockModel(String stockName, String entryPrice, String stopLoss) {
        this.stockName = stockName;
        this.entryPrice = entryPrice;
        this.stopLoss = stopLoss;
    }

    private StockModel(Parcel parcel) {
        stockName = parcel.readString();
        entryPrice = parcel.readString();
        stopLoss = parcel.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(String entryPrice) {
        this.entryPrice = entryPrice;
    }

    public String getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(String stopLoss) {
        this.stopLoss = stopLoss;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(stockName);
        parcel.writeString(entryPrice);
        parcel.writeString(stopLoss);
    }
}