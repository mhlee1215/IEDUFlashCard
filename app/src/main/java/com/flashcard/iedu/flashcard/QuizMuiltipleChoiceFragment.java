package com.flashcard.iedu.flashcard;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flashcard.iedu.flashcard.domain.WordQuizMultiple;
import com.flashcard.iedu.flashcard.samples.fragment.MyFragmentTest;

public class QuizMuiltipleChoiceFragment extends Fragment {
   // String text = "";
    private View mView;
    static QuizMultipleChoiceActivity parent;
    WordQuizMultiple quiz;

    public QuizMuiltipleChoiceFragment() {
        // Required empty public constructor
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
        mView = inflater.inflate(R.layout.fragment_quiz_multiple_choice, container, false);

        TextView tView = (TextView)mView.findViewById(R.id.sampleTextView1);
        tView.setText(quiz.getWord());


//        Button btn = (Button)mView.findViewById(R.id.btn_answer1);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                replace();
//            }
//        });

        Button btn_answer1 = (Button)mView.findViewById(R.id.btn_answer1);
        btn_answer1.setText(quiz.getChoices().get(0));
        btn_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(0);
            }
        });

        Button btn_answer2 = (Button)mView.findViewById(R.id.btn_answer2);
        btn_answer2.setText(quiz.getChoices().get(1));
        btn_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(1);
            }
        });

        Button btn_answer3 = (Button)mView.findViewById(R.id.btn_answer3);
        btn_answer3.setText(quiz.getChoices().get(2));
        btn_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(2);
            }
        });

        Button btn_answer4 = (Button)mView.findViewById(R.id.btn_answer4);
        btn_answer4.setText(quiz.getChoices().get(3));
        btn_answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(3);
            }
        });

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
