package com.flashcard.iedu.flashcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class JaeoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaeo);


    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, FrontActivity.class);
        startActivity(intent);
    }
}