package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.flashcard.iedu.flashcard.R;

/**
 * VIEW_2
 */

public class LoginActivity extends AppCompatActivity {

    public final String MyLoginPreferences = "LOGIN_PREFERENCES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        System.out.println("Hello world!");
        System.out.println("My contribution!!!!");

        SharedPreferences sharedpreferences = getSharedPreferences(MyLoginPreferences, Context.MODE_PRIVATE);



    }
}
