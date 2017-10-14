package br.com.jhowcs.traderjournal.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import br.com.jhowcs.traderjournal.R;
import br.com.jhowcs.traderjournal.model.StockModel;
import br.com.jhowcs.traderjournal.repository.local.StockRepositoryLocal;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class StockRegister extends AppCompatActivity implements OnClickListener {

    public static final String EXTRA_STOCK_DATA = "stock_data";

    private EditText edtStock;
    private EditText edtEntryPrice;
    private EditText edtStopLoss;
    private AppCompatButton btnSave;
    private ViewGroup status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_register);

        status = findViewById(R.id.status_view);
        edtStock = findViewById(R.id.edtStockName);
        edtEntryPrice = findViewById(R.id.edtEntryPrice);
        edtStopLoss = findViewById(R.id.edtStopLoss);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(this);

    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, StockRegister.class);
        return intent;
    }

    @Override
    public void onClick(View view) {
        if(validateFields()) {
            status.setVisibility(GONE);
            saveToDatabase();
            finish();

        } else {
            status.setVisibility(VISIBLE);
        }
    }

    private void saveToDatabase() {
        StockModel stockModel = mapFieldsToStockObject();
        StockRepositoryLocal repositoryLocal = new StockRepositoryLocal();
        long idInserted = repositoryLocal.insert(stockModel);
        stockModel.setId(String.valueOf(idInserted));

        Intent intent = new Intent();
        intent.putExtra(EXTRA_STOCK_DATA, stockModel);
        setResult(RESULT_OK, intent);
    }

    private StockModel mapFieldsToStockObject() {
        StockModel stockModel = new StockModel(edtStock.getText().toString(),
                edtEntryPrice.getText().toString(),
                edtStopLoss.getText().toString());

        return stockModel;
    }

    private boolean validateFields() {
        return edtStock.getText().length() > 0
                && edtEntryPrice.getText().length() > 0
                && edtStopLoss.getText().length() > 0;
    }
}
