package com.flashcard.iedu.flashcard;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.flashcard.iedu.flashcard.R;
import com.flashcard.iedu.flashcard.samples.slideViewer.DepthPageTransformer;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;

import java.util.ArrayList;
import java.util.List;

import edu.iedu.flashcard.dao.domain.Word;
import edu.iedu.flashcard.service.WordService;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class CardListMainActivity extends AppCompatActivity {

    String actionBarTitle = "FlashCard";
    int wordbookId;
    VerticalViewPager mViewPager;
    CardListPagerAdapter cardListPagerAdapter;
    Word listOfCards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.flashcard_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //int newInteger;
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
        System.out.println("IN CARDLIST-WORDBOOK_ID>>>" + wordbookId);
        getWords(wordbookId);

        //context=this;

//        List<Word> cards = new ArrayList<Word>();
//
//        //List<String> word = new ArrayList<String>();
//        //List<String> meaning = new ArrayList<String>();
//
//        for(int i = 0 ; i < 14 ; i ++){
//            cards.add(new Word("word " + i, "meaning "+i, i));
//        }
//
//        cardListPagerAdapter = new CardListPagerAdapter(getSupportFragmentManager(), cards);
//
//        mViewPager = (VerticalViewPager) findViewById(R.id.pager);
//        mViewPager.setAdapter(cardListPagerAdapter);
        //mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        //mViewPager.setPageTransformer(true, new DepthPageTransformer());
        //mViewPager.setPageTransformer(true, new VerticalPageTransformer());


    }

    public void startCards(List<Word> cards){
        cardListPagerAdapter = new CardListPagerAdapter(getSupportFragmentManager(), cards);

        mViewPager = (VerticalViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(cardListPagerAdapter);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public static class CardListPagerAdapter extends FragmentPagerAdapter {
        List<Word> cards;
        FragmentManager fm;

        public CardListPagerAdapter(FragmentManager fm, List<Word> cards) {
            super(fm);
            this.fm = fm;
            this.cards = cards;
        }


        @Override
        public Fragment getItem(int i) {

            Bundle args = new Bundle();
//            args.putString(CardFragment.CARD_QUESTION, word.get(i));
//            args.putString(CardFragment.CARD_ANSWER, meaning.get(i));
//            args.putInt(CardFragment.CARD_POSITION_KEY, i);
            args.putInt(CardFragment.MAX_KEY, getCount());
            Fragment fragment = CardFragment.newInstance(i, getCount(), 40, cards.get(i));
            fragment.setArguments(args);
            //Fragment fragment = new MyFragmentOne();
            return fragment;

        }

        @Override
        public int getCount() {
            return cards.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Section " + (position + 1);
        }
    }

    public void getWords(int wordbookId) {
        Connection conn = new Connection();
        conn.doInBackground(wordbookId);
    }

    private class Connection extends AsyncTask {
        @Override
        protected Object doInBackground(Object... arg0) {
            int wordBookId = (Integer) arg0[0];
            List<Word> cards = WordService.getWordList(wordBookId);
            System.out.println("CARDS!?"+cards);
           // wordList.addAll(test);
            //adapter.notifyDataSetChanged();

//            List<Word> cards = new ArrayList<Word>();
//
//            //List<String> word = new ArrayList<String>();
//            //List<String> meaning = new ArrayList<String>();
//
//            for(int i = 0 ; i < 14 ; i ++){
//                cards.add(new Word("word " + i, "meaning "+i, i));
//            }


            startCards(cards);


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
