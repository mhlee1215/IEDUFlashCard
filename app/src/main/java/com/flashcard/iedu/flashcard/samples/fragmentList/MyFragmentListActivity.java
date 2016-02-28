package com.flashcard.iedu.flashcard.samples.fragmentList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.flashcard.iedu.flashcard.R;
import com.flashcard.iedu.flashcard.samples.fragmentList.dummy.DummyContent;

public class MyFragmentListActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment_list);


        ItemFragment listFragment = new ItemFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_one, listFragment).commit();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
