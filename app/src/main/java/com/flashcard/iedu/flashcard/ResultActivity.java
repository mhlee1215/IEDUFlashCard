package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flashcard.iedu.flashcard.R;

import java.util.ArrayList;
import java.util.List;

import edu.iedu.flashcard.dao.domain.Word;
import edu.iedu.flashcard.service.UserService;
import edu.iedu.flashcard.service.WordService;

public class ResultActivity extends AppCompatActivity {

    ListView lv;
    Context context;
    List<Word> wordList;
    ResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        context = this;

        wordList = new ArrayList<Word>();//
        //WordService.getWordList(1);

        lv = (ListView) findViewById(R.id.listView);

        adapter = new ResultAdapter(this, wordList);
        lv.setAdapter(adapter);

        getWords();
    }

    public void sendMessage (View view) {
        TextView viewMain =(TextView) view.findViewById(R.id.textViewMain);
        Toast.makeText(context, "You Clicked flashcards ", Toast.LENGTH_LONG).show();
    }

    public void sendMessage1 (View view) {
        TextView viewMain =(TextView) view.findViewById(R.id.textViewMain);
        Toast.makeText(context, "You clicked Learn", Toast.LENGTH_LONG).show();
    }
    public void sendMessage2 (View view) {
        TextView viewMain = (TextView) view.findViewById(R.id.textViewMain);
        Toast.makeText( context, "You clicked Match", Toast.LENGTH_LONG).show();
    }

    public void getWords(){
        Connection conn = new Connection();
        conn.doInBackground(1);
    }

    private class Connection extends AsyncTask {
        @Override
        protected Object doInBackground(Object... arg0) {
            int wordBookId = Integer.parseInt((String)arg0[0]);
            List<Word> test = WordService.getWordList(wordBookId);
            wordList.addAll(test);
            adapter.notifyDataSetChanged();
            return null;
        }
    }
}