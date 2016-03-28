package com.flashcard.iedu.flashcard.samples.slideViewer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flashcard.iedu.flashcard.R;

/**
 * Created by mhlee on 3/20/16.
 */
public class CardListFlipFragment extends Fragment
        {

    public static final String ARG_CARD_TITLE = "CARD_TITLE";
    public static final String ARG_CARD_SUB_TITLE = "CARD_SUB_TITLE";

    String frontTitle = "Front title";
    String frontSubTitle = "Front Sub title";
    String backTitle = "Back title";
    String backSubTitle = "Back Sub title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_card_list_fragment_card_front, container, false);

//        Bundle args = new Bundle();
//        args.putString(CardListFlipFragment.ARG_CARD_TITLE, "front Title");
//        args.putString(CardListFlipFragment.ARG_CARD_SUB_TITLE, "front SubTitle");
//        Fragment frontFragment = new CardFrontFragment();
//        frontFragment.setArguments(args);
//
//        getFragmentManager()
//                .beginTransaction()
//                .add(R.id.container, frontFragment)
//                .commit();

        return view;
    }


    public void flipMethod(View view){
        System.out.println("Flip!");
    }

    /**
     * A fragment representing the front of the card.
     */
//    public static class CardFrontFragment extends Fragment {
//
//
//        public CardFrontFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View view = inflater.inflate(R.layout.activity_card_list_fragment_card_front, container, false);
//
//            return view;
//        }
//
//
//        @Override
//        public void onStart() {
//            super.onStart();
//
//
//
//            Bundle args = getArguments();
//            ((TextView) getActivity().findViewById(R.id.title)).setText(
//                    args.getString(CardListFlipFragment.ARG_CARD_TITLE));
//            ((TextView) getActivity().findViewById(R.id.subtitle)).setText(
//                    args.getString(com.flashcard.iedu.flashcard.samples.card_list.CardListFlipFragment.ARG_CARD_SUB_TITLE));
//
////            TextView frontTitleView = (TextView)getActivity().findViewById(R.id.title);
////            frontTitleView.setText(title);
////            TextView frontSubTitleView = (TextView)getActivity().findViewById(R.id.subtitle);
////            frontSubTitleView.setText(subTitle);
//        }
//    }
//
//    /**
//     * A fragment representing the back of the card.
//     */
//    public static class CardBackFragment extends Fragment {
//
//        public CardBackFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            return inflater.inflate(R.layout.activity_card_list_fragment_card_back, container, false);
//        }
//
//        @Override
//        public void onStart() {
//            super.onStart();
//            Bundle args = getArguments();
//            ((TextView) getActivity().findViewById(R.id.title)).setText(
//                    args.getString(com.flashcard.iedu.flashcard.samples.card_list.CardListFlipFragment.ARG_CARD_TITLE));
//            ((TextView) getActivity().findViewById(R.id.subtitle)).setText(
//                    args.getString(com.flashcard.iedu.flashcard.samples.card_list.CardListFlipFragment.ARG_CARD_SUB_TITLE));
//
//        }
//    }

}
