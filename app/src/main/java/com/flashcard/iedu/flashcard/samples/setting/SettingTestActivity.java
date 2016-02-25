package com.flashcard.iedu.flashcard.samples.setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flashcard.iedu.flashcard.R;

public class SettingTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_test);

    }

    private void sendMessage(View view){
        //Intent intent = new Intent(this, AppPreferences.class);
        //startActivity(intent);
        System.out.println("hi");
    }
}
