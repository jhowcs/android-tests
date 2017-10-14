package br.com.jhowcs.traderjournal.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import br.com.jhowcs.traderjournal.R;
import br.com.jhowcs.traderjournal.adapter.StockAdapter;
import br.com.jhowcs.traderjournal.model.StockModel;
import br.com.jhowcs.traderjournal.repository.local.StockRepositoryLocal;

public class MainActivity extends AppCompatActivity {

    private static int RC_MAIN = 10;

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private StockAdapter adapter;
    private List<StockModel> stockData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvStocks);
        fab = findViewById(R.id.fab);

        initAdapter();
        initRecyclerView();
        setFabListener();
    }

    private void setFabListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(StockRegister.newIntent(MainActivity.this), RC_MAIN);
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    private void initAdapter() {
        adapter = new StockAdapter();
        adapter.setData(getStockData());
    }

    public List<StockModel> getStockData() {
        StockRepositoryLocal  repositoryLocal = new StockRepositoryLocal();
        return repositoryLocal.getAll();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == RC_MAIN) {
            StockModel stock = data.getParcelableExtra(StockRegister.EXTRA_STOCK_DATA);

            if (stock != null) {
                adapter.updateData(stock);
            }
        }
    }
}