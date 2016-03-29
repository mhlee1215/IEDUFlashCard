package com.flashcard.iedu.flashcard;

/**
 * Created by Aiden on 3/20/16.
 */
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String language = "en";
        Context context = this.getApplicationContext();
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(language);
        res.updateConfiguration(conf, dm);

        setContentView(R.layout.activity_main);
    }

    public final static String SIGNUP = "com.mycompany.myfirstapp.USERNAME";

    public void activitysignup(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void activitylogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }


}

