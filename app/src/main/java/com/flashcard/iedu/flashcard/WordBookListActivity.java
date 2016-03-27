package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class WordBookListActivity extends AppCompatActivity {

    ListView lv;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordbook_list);

        context=this;

        List<String> mainColumn = new ArrayList<String>();
        List<String> subColumn1 = new ArrayList<String>();

        for(int i = 0 ; i < 30 ; i ++){
            mainColumn.add("main "+i);
            subColumn1.add("sub1 "+i);
        }


        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new WordBookListAdapter(this, mainColumn,subColumn1));
    }
}
