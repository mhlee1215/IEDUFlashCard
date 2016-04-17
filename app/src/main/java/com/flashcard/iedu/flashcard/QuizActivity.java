package com.flashcard.iedu.flashcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView textDefinition = (TextView)findViewById(R.id.text_definition);
        textDefinition.setText("My Definition");
    }


}
