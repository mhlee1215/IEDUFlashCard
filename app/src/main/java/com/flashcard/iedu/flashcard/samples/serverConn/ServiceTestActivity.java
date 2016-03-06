package com.flashcard.iedu.flashcard.samples.serverConn;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flashcard.iedu.flashcard.R;

import edu.iedu.flashcard.service.UserService;

public class ServiceTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void sendMessage(View view){

        Connection conn = new Connection();
        conn.doInBackground();

    }

    private class Connection extends AsyncTask {
        @Override
        protected Object doInBackground(Object... arg0) {
            System.out.println(UserService.getUsers());
            return null;
        }
    }
}
