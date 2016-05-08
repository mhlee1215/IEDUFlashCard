package com.flashcard.iedu.flashcard;

/**
 * Created by Yujin on 4/17/2016.
 */
public class Title {
    String title;
    int position;

    public Title (String title, int position) {
        this.title = title;
        this.position = position;
    }

    public int getPosition () {return position;}
    public void setPosition (int position) {this.position = position;}
    public String getTitle() {return title;}
    public void setTitle (String title) {this.title = title;}
}
