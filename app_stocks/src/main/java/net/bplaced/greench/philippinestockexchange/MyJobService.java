package net.bplaced.greench.philippinestockexchange;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i("TAG", "job start");
        StocksApp app = (StocksApp) getApplication();
        app.update(false);
        jobFinished(params, true);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }

}
