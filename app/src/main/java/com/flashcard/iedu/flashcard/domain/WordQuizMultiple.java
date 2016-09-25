package com.flashcard.iedu.flashcard.domain;

import java.util.List;

/**
 * Created by mhlee on 9/25/16.
 */
public class WordQuizMultiple {

    String word;
    List<String> choices;
    int answer;

    public WordQuizMultiple(String word, List<String> choices, int answer){
        this.word = word;
        this.choices = choices;
        this.answer = answer;
    }

    public String getWord() {
        return word;
    }

    public List<String> getChoices() {
        return choices;
    }

    public int getAnswer() {
        return answer;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "WordQuizMultiple{" +
                "word='" + word + '\'' +
                ", choices=" + choices +
                ", answer=" + answer +
                '}';
    }
}
