package com.flashcard.iedu.flashcard.samples.serverConn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flashcard.iedu.flashcard.R;

//import edu.iedu.flashcard.service.UserService;

public class ServiceTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
    }

    public void sendMessage(View view){

        //System.out.println(UserService.getUsers());

    }
}
