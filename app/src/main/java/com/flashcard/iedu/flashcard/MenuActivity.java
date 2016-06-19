package com.flashcard.iedu.flashcard;

import android.content.Context;
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


        context = this;

        wordbookList = new ArrayList<WordBook>();
        //WordBookService.getWordBookList(1);

        lv = (ListView) findViewById(R.id.ListView);
        adapter = new MenuAdapter(this, wordbookList);
        lv.setAdapter(adapter);

        getWordBooks ();

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
    public void getWordBooks () {
        Connection conn = new Connection ();
        conn.doInBackground (1);
    }
    private class Connection extends AsyncTask {

        protected Object doInBackground(Object... arg0) {
            int wordBookId = (Integer)arg0[0];
            //List<WordBook> test = WordService.getWordBookList(wordBookId);
            List<WordBook> test = new ArrayList<WordBook>();
            for (int i = 0 ; i < 30 ; i++){
                test.add(new WordBook("dummy wordbook_"+i));

            }
            wordbookList.addAll(test);
            adapter.notifyDataSetChanged();
            return null;
        }
    }

}
