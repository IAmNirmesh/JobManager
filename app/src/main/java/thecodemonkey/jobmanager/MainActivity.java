package thecodemonkey.jobmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import thecodemokey.job.Job;
import thecodemokey.job.JobManager;
import thecodemokey.job.Task;
import thecodemokey.job.TaskCallback;

public class MainActivity extends AppCompatActivity {

    private Button startBtn, cancelBtn, startBtn1, cancelBtn1, cancelAllBtn;
    private ProgressBar progressBar, progressBar1;
    private int taskId = -1, taskId2 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.start);
        startBtn.setOnClickListener(startClickListener);
        cancelBtn = (Button) findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(cancelClickListener);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        startBtn1 = (Button) findViewById(R.id.start2);
        startBtn1.setOnClickListener(startClickListener2);
        cancelBtn1 = (Button) findViewById(R.id.cancel2);
        cancelBtn1.setOnClickListener(cancelClickListener2);

        cancelAllBtn = (Button) findViewById(R.id.cancelAl);
        cancelAllBtn.setOnClickListener(cancelAllClickListener);

        progressBar1 = (ProgressBar) findViewById(R.id.progressBar2);
    }

    View.OnClickListener startClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            taskId = JobManager.getInstance().addJob(
                    new Job<String>(new Task1(), new Task<String>() {

                        @Override
                        public String executeTask() throws Exception {

                            Thread.sleep(10000);
                            return "Done";
                        }

                    }));
        }
    };

    View.OnClickListener cancelClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if (taskId != -1) {
                JobManager.cancelTask(taskId);
            }
        }
    };

    View.OnClickListener startClickListener2 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            taskId2 = JobManager.getInstance().addJob(
                    new Job<String>(new Task2(), new Task<String>() {

                        @Override
                        public String executeTask() throws Exception {

                            Thread.sleep(60000);
                            return "Done";
                        }
                    }));
        }
    };

    View.OnClickListener cancelClickListener2 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (taskId2 != -1) {
                JobManager.cancelTask(taskId2);
            }
        }
    };

    View.OnClickListener cancelAllClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            JobManager.cancelAllTask();
        }
    };

    private class Task1 implements TaskCallback<String> {

        @Override
        public void onStart() {
            progressBar.setVisibility(View.VISIBLE);
            showToast("Task1 Started");
        }

        @Override
        public void onSuccess(String obj) {
            progressBar.setVisibility(View.GONE);
            showToast("Task 1 Result: " + obj);
        }

        @Override
        public void onFailure(Exception exception) {
            progressBar.setVisibility(View.GONE);
            showToast("Task 1: caught exception");
        }
    }

    private class Task2 implements TaskCallback<String> {

        @Override
        public void onStart() {
            progressBar1.setVisibility(View.VISIBLE);
            showToast("Task2 Started");
        }

        @Override
        public void onSuccess(String obj) {
            progressBar1.setVisibility(View.GONE);
            showToast("Task 2 Result : " + obj);
        }

        @Override
        public void onFailure(Exception exception) {
            progressBar1.setVisibility(View.GONE);
            showToast("Task 2: caught exception");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
