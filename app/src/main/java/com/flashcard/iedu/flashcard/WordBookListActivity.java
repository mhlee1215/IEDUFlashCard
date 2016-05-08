package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flashcard.iedu.flashcard.R;

import java.util.ArrayList;
import java.util.List;

import edu.iedu.flashcard.dao.domain.Word;

public class WordBookListActivity extends AppCompatActivity {

    ListView lv;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordbook_list);

        context = this;

        List<Word> cardList = new ArrayList<Word>();
        //List<String> subColumn1 = new ArrayList<String>();

        for (int i = 0; i < 30; i++) {
            cardList.add(new Word("word_"+i, "meaning_"+i, i));
            //subColumn1.add("sub1 " + i);
        }


        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new WordBookListAdapter(this, cardList));
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
}