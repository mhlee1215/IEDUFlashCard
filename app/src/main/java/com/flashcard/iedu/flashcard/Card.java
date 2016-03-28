package com.flashcard.iedu.flashcard;

/**
 * Created by mhlee on 3/27/16.
 */
public class Card {
    String word;
    String meaning;
    int position;

    public Card(String word, String meaning, int position){
        this.word = word;
        this.meaning = meaning;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
