package net.bplaced.greench.philippinestockexchange.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.bplaced.greench.philippinestockexchange.R;
import net.bplaced.greench.philippinestockexchange.StocksApp;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        StocksApp app = (StocksApp) getApplication();
        app.loadData(this);
    }
}
