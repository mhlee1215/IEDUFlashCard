package com.flashcard.iedu.flashcard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.flashcard.iedu.flashcard.R;
import com.flashcard.iedu.flashcard.samples.slideViewer.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class CardListMainActivity extends AppCompatActivity {

    VerticalViewPager mViewPager;
    CardListPagerAdapter cardListPagerAdapter;
    Card listOfCards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list_main);


        //context=this;

        List<Card> cards = new ArrayList<Card>();

        //List<String> word = new ArrayList<String>();
        //List<String> meaning = new ArrayList<String>();

        for(int i = 0 ; i < 14 ; i ++){
            cards.add(new Card("word " + i, "meaning "+i, i));
        }

        cardListPagerAdapter = new CardListPagerAdapter(getSupportFragmentManager(), cards);

        mViewPager = (VerticalViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(cardListPagerAdapter);
        //mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        //mViewPager.setPageTransformer(true, new DepthPageTransformer());
        //mViewPager.setPageTransformer(true, new VerticalPageTransformer());


    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public static class CardListPagerAdapter extends FragmentPagerAdapter {
        List<Card> cards;
        FragmentManager fm;

        public CardListPagerAdapter(FragmentManager fm, List<Card> cards) {
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
}
