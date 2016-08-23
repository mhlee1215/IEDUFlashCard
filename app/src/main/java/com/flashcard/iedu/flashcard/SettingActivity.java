package com.flashcard.iedu.flashcard;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.flashcard.iedu.flashcard.R;

/**
 * VIEW_9
 */

public class SettingActivity extends AppCompatActivity {

    String actionBarTitle = "Setting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //addPreferencesFromResource(R.xml.preferences);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setLogo(R.drawable.ic_logo);

        getSupportActionBar().setDisplayUseLogoEnabled(false);


        getFragmentManager().beginTransaction().replace(R.id.content_frame, new SettingFragment()).commit();
    }
}
