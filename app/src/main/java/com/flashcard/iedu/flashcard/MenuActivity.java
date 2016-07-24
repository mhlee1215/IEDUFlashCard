package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.flashcard.iedu.flashcard.R;

import java.util.ArrayList;
import java.util.List;

import edu.iedu.flashcard.dao.domain.WordBook;
import edu.iedu.flashcard.service.WordBookService;
import edu.iedu.flashcard.service.WordService;

/**
 * VIEW_4
 */

public class MenuActivity extends AppCompatActivity {
    String actionBarTitle = "WordBook List";

    Context context;
    ListView lv;
    List<WordBook> wordbookList;
    MenuAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(actionBarTitle);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        context = this;

        wordbookList = new ArrayList<>();
        //WordBookService.getWordBookList(1);


        Integer newInteger;
//        if (savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if (extras == null) {
//                newInteger = null;
//            } else {
//                newInteger = extras.getInt("USER_ID");
//            }
//        } else {
//            newInteger = (Integer) savedInstanceState.getSerializable("USER_ID");
//
//        }
//
//        System.out.println("MENUACTIVITY_USERID:"+newInteger);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("IEDUPref", 0); // 0 - for private mode
        newInteger = pref.getInt("USER_ID", -1);
        System.out.println("MENUACTIVITY_USERID:"+newInteger);

        adapter = new MenuAdapter(this, wordbookList, newInteger);
        lv = (ListView) findViewById(R.id.ListView);
        lv.setAdapter(adapter);

        getWordBooks(newInteger);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);

        return true;
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
    public void getWordBooks (int wordBookId) {
        Connection conn = new Connection ();
        conn.doInBackground(wordBookId);
    }
    private class Connection extends AsyncTask {

        protected Object doInBackground(Object... arg0) {
            int userId = (Integer)arg0[0];
            List<WordBook> test = WordBookService.getWordBooks(userId);


            wordbookList.addAll(test);
            adapter.notifyDataSetChanged();
            return null;
        }
    }

}
