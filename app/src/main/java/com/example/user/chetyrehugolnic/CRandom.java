package com.example.user.chetyrehugolnic;

import android.support.annotation.NonNull;

/**
 * Created by sokol on 19.08.2018.
 */

public class CRandom implements Comparable<CRandom> {


    public CRandom(int usd, int ran, String aName, String aDate, String aTime, String aTitle,String Description,boolean aZapis,String atopics){

        user_id=usd;
       random_id=ran;
       name=aName;
        date=aDate;
        time=aTime;
        title=aTitle;
        Zapis=aZapis;
        description=Description;
        topics=atopics;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getRandom_id() {

        return random_id;
    }

    public boolean isZapis() {
        return Zapis;
    }

    public String getDescription() {
        return description;
    }

    public String getTopics() {
        return topics;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public boolean isCourse(){
        return false;
    }

    protected String title,date,time,description,topics,name;

    protected CUsers name_event;
    protected int user_id,random_id;
    protected boolean Zapis;

    @Override
    public int compareTo(@NonNull CRandom o) {

        return 0;
    }
}
