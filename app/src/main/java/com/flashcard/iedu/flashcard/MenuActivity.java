package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
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

import edu.iedu.flashcard.dao.domain.WordBook;
import edu.iedu.flashcard.service.WordBookService;
import edu.iedu.flashcard.service.WordService;

/**
 * VIEW_4
 */

public class MenuActivity extends AppCompatActivity {
    Context context;
    ListView lv;
    List<WordBook> wordbookList;
    MenuAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        context = this;

        wordbookList = new ArrayList<WordBook>();
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
