package com.example.user.chetyrehugolnic;

/**
 * Created by sokol on 13.08.2018.
 */

public class CRecords {
    public CRecords(int usd,int cid,int eid){
    user_id=usd;
    course_id=cid;
    event_id=eid;

    }

    public int getUser_id() {
        return user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    protected int user_id,course_id,event_id;
}
