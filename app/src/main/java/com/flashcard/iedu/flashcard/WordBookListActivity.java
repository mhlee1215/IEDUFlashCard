package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import edu.iedu.flashcard.dao.domain.Word;
import edu.iedu.flashcard.service.WordService;

public class WordBookListActivity extends AppCompatActivity {

    String actionBarTitle = "WordBook List";
    int wordbookId;
    String wordbookName;
    ListView lv;
    Context context;
    List<Word> wordList;
    WordBookListAdapter adapter;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordbook_list);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.wordbook_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setLogo(R.drawable.ic_logo);

        getSupportActionBar().setDisplayUseLogoEnabled(false);

        wordList = new ArrayList<Word>();//
        //WordService.getWordList(1);

        lv = (ListView) findViewById(R.id.listView);
        adapter = new WordBookListAdapter(this, wordList);
        lv.setAdapter(adapter);
        //int newInteger;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                wordbookId = -1;
                wordbookName = "";
            } else {
                wordbookId = extras.getInt("WORDBOOK_ID");
                wordbookName = extras.getString("WORDBOOK_NAME");
            }
        }else {
            wordbookId = (Integer) savedInstanceState.getSerializable("WORDBOOK_ID");
            wordbookName = (String) savedInstanceState.getSerializable("WORDBOOK_NAME");
        }
        System.out.println("WORDBOOK_ID>>>"+wordbookId);
        System.out.println("WORDBOOK_NAME>>>"+wordbookName);
        getWords(wordbookId);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        Button buttonFlashcard = (Button)findViewById(R.id.button_flashcard);
        buttonFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WordBookListActivity.this, CardListMainActivity.class);
                i.putExtra("WORDBOOK_ID", wordbookId);
                startActivity(i);
            }
        });

        Button buttonLearn = (Button)findViewById(R.id.button_learn);
        buttonLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WordBookListActivity.this, QuizActivity.class);
                i.putExtra("WORDBOOK_ID", wordbookId);
                startActivity(i);
            }
        });

        Button buttonMultiple = (Button)findViewById(R.id.button_multiple);
        buttonMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WordBookListActivity.this, QuizMultipleChoiceActivity.class);
                i.putExtra("WORDBOOK_ID", wordbookId);
                startActivity(i);
            }
        });

        TextView newtext = (TextView) findViewById(R.id.textView1);
        newtext.setText(wordbookName);

        TextView numbertext = (TextView) findViewById(R.id.textView2);
        numbertext.setText(wordList.size() + " terms created by");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wordbook_actionbar_options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWords(wordbookId);
    }




    public void startCard(){
        Intent i = new Intent(WordBookListActivity.this, CardListMainActivity.class);
        i.putExtra("WORDBOOK_ID", wordbookId);
        startActivity(i);
    }


//    public void sendMessage(View view) {
//        TextView viewMain = (TextView) view.findViewById(R.id.textViewMain);
//        Toast.makeText(context, "You Clicked flashcards ", Toast.LENGTH_LONG).show();
//    }
//
//    public void sendMessage1(View view) {
//        TextView viewMain = (TextView) view.findViewById(R.id.textViewMain);
//        Toast.makeText(context, "You clicked Learn", Toast.LENGTH_LONG).show();
//    }

    public void sendMessage2(View view) {
        TextView viewMain = (TextView) view.findViewById(R.id.textViewMain);
        Toast.makeText(context, "You clicked Match", Toast.LENGTH_LONG).show();
    }

    public void getWords(int userId) {
        Connection conn = new Connection();
        conn.doInBackground(userId);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "WordBookList Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.flashcard.iedu.flashcard/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "WordBookList Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.flashcard.iedu.flashcard/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private class Connection extends AsyncTask {
        @Override
        protected Object doInBackground(Object... arg0) {
            int wordBookId = (Integer) arg0[0];
            List<Word> test = WordService.getWordList(wordBookId);

            wordList.clear();
            wordList.addAll(test);
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
            case R.id.action_add:
                Intent wordEditActivity = new Intent(context, WordEditActivity.class);
                wordEditActivity.putExtra("WORDBOOK_ID",wordbookId);
                context.startActivity(wordEditActivity);
                return true;
//            case R.id.action_info:
//                Intent wordInfoActivity = new Intent(context, WordBookInfoActivity.class);
//                context.startActivity(wordInfoActivity);
//                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}