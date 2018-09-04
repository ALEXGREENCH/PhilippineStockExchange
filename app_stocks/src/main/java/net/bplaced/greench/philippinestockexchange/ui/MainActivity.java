package net.bplaced.greench.philippinestockexchange.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import net.bplaced.greench.philippinestockexchange.R;
import net.bplaced.greench.philippinestockexchange.StocksApp;
import net.bplaced.greench.philippinestockexchange.model.Stocks;
import net.bplaced.greench.philippinestockexchange.ui.adapter.ListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list)
    RecyclerView list;
    StocksApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        app = (StocksApp) getApplication();
        Stocks stocks = app.getStocks();

        ListAdapter listAdapter = new ListAdapter(stocks.getStock());
        app.setList_updater(listAdapter);

        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(listAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:
                app.manual_update();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        app.stopScheduler();
        super.onDestroy();
    }
}
