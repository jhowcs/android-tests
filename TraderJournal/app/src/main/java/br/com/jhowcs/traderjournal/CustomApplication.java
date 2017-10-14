package br.com.jhowcs.traderjournal;

import android.app.Application;

import br.com.jhowcs.traderjournal.repository.local.DatabaseProvider;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseProvider.init(this);
    }
}
