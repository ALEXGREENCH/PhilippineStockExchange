package net.bplaced.greench.philippinestockexchange;

import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import net.bplaced.greench.philippinestockexchange.model.Stocks;
import net.bplaced.greench.philippinestockexchange.net.Client;
import net.bplaced.greench.philippinestockexchange.ui.MainActivity;
import net.bplaced.greench.philippinestockexchange.ui.adapter.IUpdate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StocksApp extends Application {

    private Stocks stocks;
    private IUpdate list_updater;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void loadData(final Context c) {
        Client.call().enqueue(new Callback<Stocks>() {
            @Override
            public void onResponse(Call<Stocks> call, Response<Stocks> response) {
                if (response.code() != 200)
                    return;

                stocks = response.body();

                Intent intent = new Intent(c, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
                sendToScheduler();
            }

            @Override
            public void onFailure(Call<Stocks> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                System.exit(0);
            }
        });
    }


    void update(final boolean manual_update){
        Client.call().enqueue(new Callback<Stocks>() {
            @Override
            public void onResponse(Call<Stocks> call, Response<Stocks> response) {
                if (response.code() != 200)
                    return;
                stocks = response.body();
                if (list_updater != null && stocks != null)
                    list_updater.setStocks(stocks.getStock());
                sendToScheduler();

                if (manual_update)
                    Toast.makeText(StocksApp.this, "Обновлено!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Stocks> call, Throwable t) {
                Log.i("TAG", t.getMessage());
            }
        });
    }


    public Stocks getStocks() {
        return stocks;
    }

    public void setList_updater(IUpdate list_updater) {
        this.list_updater = list_updater;
    }


    int JOB_ID = 101;
    int time_update = 15000;
    JobScheduler jobScheduler;

    void sendToScheduler() {
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName jobService = new ComponentName(getPackageName(), MyJobService.class.getName());
        JobInfo jobInfo =
                new JobInfo.Builder(JOB_ID, jobService)
                        .setPeriodic(time_update)
                        .build();

        int jobId = jobScheduler.schedule(jobInfo);
        if (jobScheduler.schedule(jobInfo) > 0) {
            Log.i("TAG", "Successfully scheduled job: " + jobId);
        } else {
            Log.i("TAG", "RESULT_FAILURE: " + jobId);
        }
    }

    public void manual_update(){
        jobScheduler.cancelAll();
        update(true);
    }

    public void stopScheduler(){
        jobScheduler.cancelAll();
    }
}
