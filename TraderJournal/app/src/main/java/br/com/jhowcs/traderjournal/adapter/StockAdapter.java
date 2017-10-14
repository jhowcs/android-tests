package br.com.jhowcs.traderjournal.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.jhowcs.traderjournal.R;
import br.com.jhowcs.traderjournal.model.StockModel;

public class StockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    private List<StockModel> stockModelList;

    private static final int VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_EMPTY_STATE = 1;

    @Override
    public int getItemViewType(int position) {
        return stockModelList.isEmpty()
                ? VIEW_TYPE_EMPTY_STATE
                : VIEW_TYPE_DATA;
    }

    public void setData(List<StockModel> data) {
        stockModelList = data;
    }

    public void updateData(StockModel data) {
        stockModelList.add(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        if (viewType == VIEW_TYPE_DATA) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_stock_portifolio, parent, false);
            viewHolder = new StockViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_stock_empty_state, parent, false);
            viewHolder = new StockEmptyStateViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StockViewHolder) {
            StockModel stockModel = stockModelList.get(position);
            ((StockViewHolder) holder).bind(stockModel);
        }
    }

    @Override
    public int getItemCount() {
        return stockModelList.isEmpty() ? 1 : stockModelList.size();
    }

    static class StockViewHolder extends RecyclerView.ViewHolder {
        private TextView txtStockName;
        private TextView txtEntryPrice;
        private TextView txtStopLoss;

        public StockViewHolder(View itemView) {
            super(itemView);
            txtStockName = itemView.findViewById(R.id.txtStockName);
            txtEntryPrice = itemView.findViewById(R.id.txtEntryPrice);
            txtStopLoss = itemView.findViewById(R.id.txtStopLoss);
        }

        public void bind(StockModel stockModel) {
            txtStockName.setText(stockModel.getStockName());
            txtEntryPrice.setText(stockModel.getEntryPrice());
            txtStopLoss.setText(stockModel.getStopLoss());
        }
    }

    static class StockEmptyStateViewHolder extends RecyclerView.ViewHolder {
        public StockEmptyStateViewHolder(View itemView) {
            super(itemView);
        }
    }
}