package com.flashcard.iedu.flashcard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flashcard.iedu.flashcard.samples.fragment.MyFragmentTest;

public class QuizMuiltipleChoiceFragment extends Fragment {
    String text = "";
    private View mView;
    static MyFragmentTest parent;

    public QuizMuiltipleChoiceFragment() {
        // Required empty public constructor
    }

    public void setText(String text){
        this.text = text;
    }

    public void setParent(MyFragmentTest parent){
        this.parent = parent;
        System.out.println("parentparent:"+parent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_quiz_multiple_choice, container, false);

        TextView tView = (TextView)mView.findViewById(R.id.sampleTextView1);
        tView.setText(text+"SAMPLE!");
        Button btn = (Button)mView.findViewById(R.id.sampleButton1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace();
            }
        });

        return mView;
    }

    public void replace(){
        System.out.println("parent: "+parent);
        parent.changeFragment("12312312");
    }
}
