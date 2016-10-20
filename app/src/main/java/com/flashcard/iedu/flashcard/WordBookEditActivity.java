package com.flashcard.iedu.flashcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.iedu.flashcard.dao.domain.WordBook;
import edu.iedu.flashcard.service.WordBookService;

public class WordBookEditActivity extends AppCompatActivity {
    String actionBarTitle = "Add wordbook";
    Integer userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordbook_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.wordedit_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setLogo(R.drawable.ic_logo);

        getSupportActionBar().setDisplayUseLogoEnabled(false);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("IEDUPref", 0); // 0 - for private mode
        userId = pref.getInt("USER_ID", -1);

        Button btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEdit = (EditText)findViewById(R.id.edit_wb_name);
                String name = nameEdit.getText().toString();

                if(name.isEmpty()){

                }else{
                    WordBook wordbook = new WordBook();
                    wordbook.setUserid(userId);
                    wordbook.setName(name);
                    WordBookService.createWordBook(wordbook);
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
