package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.flashcard.iedu.flashcard.samples.fragment.MyFragmentOne;

public class QuizMultipleChoiceActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_multiple);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.wordbook_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HHHH");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setLogo(R.drawable.ic_logo);

        getSupportActionBar().setDisplayUseLogoEnabled(false);

        QuizMuiltipleChoiceFragment fQuiz = new QuizMuiltipleChoiceFragment();
        fQuiz.setText("HELLO!!!");
        //fQuiz.setParent(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_one, fQuiz).commit();

    }


}
