package com.flashcard.iedu.flashcard;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flashcard.iedu.flashcard.domain.WordQuizMultiple;

public class QuizMuiltipleChoiceResultFragment extends Fragment {
   // String text = "";
    private View mView;
    static QuizMultipleChoiceActivity parent;
    WordQuizMultiple quiz;
    int correctNum;
    int wrongNum;

    public QuizMuiltipleChoiceResultFragment() {
        // Required empty public constructor
    }

    public void setResults(int correctNum, int wrongNum){
        this.correctNum = correctNum;
        this.wrongNum = wrongNum;
    }


    public void setQuiz(WordQuizMultiple quiz){
        this.quiz = quiz;
    }

    public void setParent(QuizMultipleChoiceActivity parent){
        this.parent = parent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_quiz_multiple_choice_result, container, false);

        TextView quizScore = (TextView)mView.findViewById(R.id.quiz_score);
        quizScore.setText(correctNum+"/"+(correctNum+wrongNum));

        TextView quizCorrectRatio = (TextView)mView.findViewById(R.id.quiz_correct_ratio);
        quizCorrectRatio.setText((Math.round(correctNum*100/(correctNum+wrongNum)))+"%");

        return mView;
    }

    public void submitAnswer(int answer){
        parent.submitAnswer(answer);
    }

//    public void replace(){
//        System.out.println("parent: "+parent);
//        //parent.changeFragment("12312312");
//    }
}
