package com.flashcard.iedu.flashcard.samples.fragment;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.flashcard.iedu.flashcard.R;

public class MyFragmentTest extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment_test);

        //FragmentManager fm = getFragmentManager();
        MyFragmentOne fOne = new MyFragmentOne();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_one, fOne).commit();
        MyFragmentTwo fTwo = new MyFragmentTwo();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_two, fTwo).commit();
    }
}
