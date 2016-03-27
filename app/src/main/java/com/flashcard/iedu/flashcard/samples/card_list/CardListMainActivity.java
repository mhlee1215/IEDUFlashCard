package com.flashcard.iedu.flashcard.samples.card_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flashcard.iedu.flashcard.R;
import com.flashcard.iedu.flashcard.samples.fragment.MyFragmentOne;
import com.flashcard.iedu.flashcard.samples.slideViewer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class CardListMainActivity extends AppCompatActivity {

//    ListView lv;
//    Context context;


    ViewPager mViewPager;
    CardListPagerAdapter cardListPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list_main);


        //context=this;

        List<String> word = new ArrayList<String>();
        List<String> meaning = new ArrayList<String>();

        for(int i = 0 ; i < 14 ; i ++){
            word.add("word "+i);
            meaning.add("meaning "+i);
        }

        cardListPagerAdapter = new CardListPagerAdapter(getSupportFragmentManager(), word, meaning);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(cardListPagerAdapter);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public static class CardListPagerAdapter extends FragmentPagerAdapter {
        List<Boolean> isBack;
        List<String> word;
        List<String> meaning;
        FragmentManager fm;
        public CardListPagerAdapter(FragmentManager fm, List<String> word, List<String> meaning) {
            super(fm);
            this.fm = fm;
            this.word = word;
            this.meaning = meaning;

            isBack = new ArrayList<Boolean>();
            for(int i = 0 ; i < word.size() ; i++){
                isBack.add(false);
            }
        }

        @Override
        public Fragment getItem(int i) {

                    // The other sections of the app are dummy placeholders.
//            Bundle args = new Bundle();
//            args.putString(CardListMainActivity.ARG_CARD_TITLE, word.get(i));
//            args.putString(CardListMainActivity.ARG_CARD_SUB_TITLE, "");
//            Fragment fragment = new CardFrontFragment();
//            fragment.setArguments(args);

//            Bundle args = new Bundle();
//            args.put


//            private final static String CARD_QUESTION = "q";
//            private final static String CARD_ANSWER = "a";
//            private final static String MAX_KEY = "m";
//            private final static String CARD_POSITION_KEY = "p";

            Bundle args = new Bundle();
            args.putString(CardFragment.CARD_QUESTION, word.get(i));
            args.putString(CardFragment.CARD_ANSWER, meaning.get(i));
            args.putInt(CardFragment.CARD_POSITION_KEY, i);
            //args.putInt(CardFragment.MAX_KEY, 10);
            Fragment fragment = CardFragment.newInstance(i, getCount(), 20);
            fragment.setArguments(args);
            //Fragment fragment = new MyFragmentOne();
            return fragment;

        }

        @Override
        public int getCount() {
            return word.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Section " + (position + 1);
        }
    }
}
