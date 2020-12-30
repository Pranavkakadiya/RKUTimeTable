package com.example.timetable;

public class ExampleItem {

    private String mCreator;
    private int mLikes;
    public ExampleItem( String creator, int likes) {

        mCreator = creator;
        mLikes = likes;
    }

    public String getCreator() {
        return mCreator;
    }
    public int getLikeCount() {
        return mLikes;
    }
}
