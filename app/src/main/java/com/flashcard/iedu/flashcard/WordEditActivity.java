package com.flashcard.iedu.flashcard;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.iedu.flashcard.dao.domain.Word;
import edu.iedu.flashcard.dao.domain.WordBook;
import edu.iedu.flashcard.service.WordBookService;
import edu.iedu.flashcard.service.WordService;

public class WordEditActivity extends AppCompatActivity {


    String actionBarTitle = "Word Edit";
    int userId;
    int wordbookId;
    String wordbookName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.wordedit_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        getSupportActionBar().setDisplayUseLogoEnabled(false);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("IEDUPref", 0); // 0 - for private mode
        userId = pref.getInt("USER_ID", -1);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                wordbookId = -1;
            } else {
                wordbookId = extras.getInt("WORDBOOK_ID");
            }
        }else {
            wordbookId = (Integer) savedInstanceState.getSerializable("WORDBOOK_ID");
        }
        System.out.println("WORDBOOK_ID>>>"+wordbookId);


        Button btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEdit = (EditText)findViewById(R.id.wd_name);
                String name = nameEdit.getText().toString();

                EditText meaningEdit = (EditText)findViewById(R.id.wd_meaning);
                String meaning = meaningEdit.getText().toString();


                if(name.isEmpty()){

                }else{
                    Word wd = new Word();
                    wd.setName(name);
                    wd.setMeaning(meaning);
                    wd.setWordbookid(wordbookId);
                    wd.setIsfavorite("N");
                    WordService.createWord(wd);
                    onBackPressed();
                }
            }
        });
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
