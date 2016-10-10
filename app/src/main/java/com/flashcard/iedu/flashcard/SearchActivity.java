package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.iedu.flashcard.dao.domain.WordBook;
import edu.iedu.flashcard.service.WordBookService;


public class SearchActivity extends AppCompatActivity {
    Context context;
    ListView lv;
    List<WordBook> wordbookList;
    SearchAdapter adapter;
    String actionBarTitle = "Wordbook Search";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        getSupportActionBar().setDisplayUseLogoEnabled(false);


        context = this;

        wordbookList = new ArrayList<WordBook>();

        lv = (ListView) findViewById(R.id.ListView);
        adapter = new SearchAdapter(this, wordbookList);
        lv.setAdapter(adapter);

        getWordBooks();

        Button btnSearch = (Button)this.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchQuery = (EditText) findViewById(R.id.query_text);
                List<WordBook> wbList = WordBookService.searchWordBooks(searchQuery.getText().toString());
                System.out.println(wbList);
                wordbookList.clear();
                wordbookList.addAll(wbList);
                adapter.notifyDataSetChanged();
            }
        });


    }

//    public void sendMessage(View view) {
//        TextView viewMain = (TextView) view.findViewById(R.id.textViewMain);
//        Toast.makeText(context, "You Clicked latest ", Toast.LENGTH_LONG).show();
//    }
//
//    public void sendMessage1(View view) {
//        TextView viewMain = (TextView) view.findViewById(R.id.textViewMain);
//        Toast.makeText(context, "You clicked set", Toast.LENGTH_LONG).show();
//    }
//
//    public void sendMessage2(View view) {
//        TextView viewMain = (TextView) view.findViewById(R.id.textViewMain);
//        Toast.makeText(context, "You clicked class", Toast.LENGTH_LONG).show();
//    }

    public void getWordBooks () {
        Connection conn = new Connection();
        conn.doInBackground(1);
    }

    private class Connection extends AsyncTask {

        protected Object doInBackground(Object... arg0) {
            int wordBookId = (Integer)arg0[0];
            //List<WordBook> test = WordService.getWordBookList(wordBookId);
            List<WordBook> test = new ArrayList<WordBook>();
            for (int i = 0; i < 30; i++) {
                test.add(new WordBook("dummy wordbook_" + i));

            }
            wordbookList.addAll(test);
            adapter.notifyDataSetChanged();
            return null;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
