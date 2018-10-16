package com.example.user.chetyrehugolnic;

/**
 * Created by sokol on 13.08.2018.
 */

public class CCourse extends CRandom {
    public CCourse(int usd, int ran,String aName,String aDate,String aTime,String aTitle,String Description,boolean aZapis,String atopics){
        super(usd,ran,aName, aDate, aTime, aTitle,Description,aZapis,atopics);


    }
    public boolean isCourse(){
        return true;
    }
}
