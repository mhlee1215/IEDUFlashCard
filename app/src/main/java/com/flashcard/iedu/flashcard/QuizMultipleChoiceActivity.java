package com.flashcard.iedu.flashcard;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.flashcard.iedu.flashcard.domain.WordQuizMultiple;

import java.util.ArrayList;
import java.util.List;

import edu.iedu.flashcard.dao.domain.Word;
import edu.iedu.flashcard.service.WordService;


public class QuizMultipleChoiceActivity extends AppCompatActivity {

    String actionBarTitle = "Multiple Choice";
    Context context;
    int curProgress = 0;
    int maxQuiz = 20;
    List<WordQuizMultiple> quizList = null;
    int wordbookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_multiple);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        context = this;

        int newInteger;
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
        //wordbookId = 9030;
        System.out.println("IN CARDLIST-WORDBOOK_ID>>>" + wordbookId);

        Toolbar toolbar = (Toolbar) findViewById(R.id.multiple_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ProgressBar pb = (ProgressBar)findViewById(R.id.mc_progressBar);
        pb.setProgress(0);


        getWords(wordbookId);
//        List<Word> words = new ArrayList<Word>();
//        for(int i = 0 ; i < 30 ; i++){
//            words.add(new Word("w_"+i, "m_"+i));
//        }
//




        Button btn_submit = (Button)findViewById(R.id.mc_button_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // changeFragment("123");
            }
        });

        Button btn_skip = (Button)findViewById(R.id.mc_button_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  changeFragment("123");
            }
        });
    }

    public void startQuiz(List<Word> words){
        quizList = genQuiz(words, maxQuiz, 4);
        System.out.println("quizList:"+quizList);
        QuizMuiltipleChoiceFragment fQuiz = new QuizMuiltipleChoiceFragment();
        fQuiz.setQuiz(quizList.get(curProgress));
        curProgress++;

        fQuiz.setParent(this);
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.replace(R.id.fragment_mc_container, fQuiz).commit();

    }


    public void getWords(int wordbookId) {
        Connection conn = new Connection();
        conn.doInBackground(wordbookId);
    }

    private class Connection extends AsyncTask {
        @Override
        protected Object doInBackground(Object... arg0) {
            int wordBookId = (Integer) arg0[0];
            List<Word> words = WordService.getWordList(wordBookId);
            System.out.println("WORDS!?"+words);

            startQuiz(words);


            return null;
        }
    }


    public List<WordQuizMultiple> genQuiz(List<Word> words, int maxQuiz, int numChoices){
        List<WordQuizMultiple> quizes = new ArrayList<>();
        List<String> allMeaning = new ArrayList<String>();
        for(Word w : words)
            allMeaning.add(w.getMeaning());

        List<Integer> answerWordsOrder = randPermutation(words.size());
        System.out.println("randPerm:"+answerWordsOrder);
        for(int i = 0 ; i < maxQuiz; i++){
            if(answerWordsOrder.size() == 0) answerWordsOrder = randPermutation(words.size());
            Word answerWord = words.get(answerWordsOrder.get(0));
            answerWordsOrder.remove(0);

            List<String> choices = new ArrayList<>();
            List<Integer> randPerm = randPermutation(allMeaning.size());
            int answer = (int)(Math.random()*numChoices);


            while( choices.size() < numChoices && randPerm.size() > 0){
                if(choices.size() == answer){
                    choices.add(answerWord.getMeaning());
                    continue;
                }

                String curMeaning = allMeaning.get(randPerm.get(0));
                randPerm.remove(0);

                if(answerWord.getMeaning().equalsIgnoreCase(curMeaning)){
                    //continue;
                }else{
                    choices.add(curMeaning);
                }
            }

            quizes.add(new WordQuizMultiple(answerWord.getName(), choices, answer));

        }

        return quizes;
    }


    public List<Integer> randPermutation(int size){
        List<Integer> iList = new ArrayList<>();
        for(int i = 0 ; i < size ; i++)
            iList.add(i);
        return randPermutation(iList);
    }

    public List<Integer> randPermutation(List<Integer> iList){
        List<Integer> randPermList = new ArrayList();

        while(iList.size() > 0){
            int next = (int)(Math.random()*iList.size());
            randPermList.add(iList.get(next));
            iList.remove(next);
        }
        return randPermList;
    }

    public void submitAnswer(int answer){

        //check answer here.
        if(quizList.get(curProgress-1).getAnswer() == answer){
            System.out.println("correct!");
        }else{
            System.out.println("wrong!");
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);

        QuizMuiltipleChoiceFragment fQuiz = new QuizMuiltipleChoiceFragment();
        fQuiz.setQuiz(quizList.get(curProgress));
        curProgress++;

        ft.replace(R.id.fragment_mc_container, fQuiz);

        ProgressBar pb = (ProgressBar)findViewById(R.id.mc_progressBar);
        pb.setProgress(curProgress * 100 / maxQuiz);

// Start the animated transition.
        ft.commit();
    }

//    public void changeFragment(String text){
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
//
//        QuizMuiltipleChoiceFragment fQuiz = new QuizMuiltipleChoiceFragment();
//        fQuiz.setQuiz(quizList.get(curProgress));
//        curProgress++;
//
//        ft.replace(R.id.fragment_mc_container, fQuiz);
//
//        ProgressBar pb = (ProgressBar)findViewById(R.id.mc_progressBar);
//        pb.setProgress(curProgress*100/maxQuiz);
//
//// Start the animated transition.
//        ft.commit();
//    }


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
