package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flashcard.iedu.flashcard.R;

import java.util.ArrayList;
import java.util.List;

/**
 * VIEW_4
 */

public class MenuActivity extends AppCompatActivity {
    Context context;
    ListView lv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);


        context = this;

        List<Title> titleList = new ArrayList<Title>();

        for (int i = 0; i < 15; i++) {
            titleList.add(new Title("title_" + i, i));
        }

        lv = (ListView) findViewById(R.id.ListView);
        lv.setAdapter(new MenuAdapter(this, titleList));

    }
    public void sendMessage (View view) {
        TextView viewMain =(TextView) view.findViewById(R.id.textViewMain);
        Toast.makeText(context, "You Clicked latest ", Toast.LENGTH_LONG).show();
    }

    public void sendMessage1 (View view) {
        TextView viewMain =(TextView) view.findViewById(R.id.textViewMain);
        Toast.makeText(context, "You clicked set", Toast.LENGTH_LONG).show();
    }
    public void sendMessage2 (View view) {
        TextView viewMain = (TextView) view.findViewById(R.id.textViewMain);
        Toast.makeText( context, "You clicked class", Toast.LENGTH_LONG).show();
    }

}
