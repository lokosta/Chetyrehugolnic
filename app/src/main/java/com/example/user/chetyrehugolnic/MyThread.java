package com.example.user.chetyrehugolnic;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class MyThread {

   static database db=Data_Base.instance();

    public static class TGetEvent extends Thread{
        Exchanger<ArrayList<CEvent>>exchanger=new Exchanger<>();


        public TGetEvent(Exchanger<ArrayList<CEvent>> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                exchanger.exchange(db.getEventdata());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public static class TGetCourse extends Thread{
        Exchanger<ArrayList<CCourse>>exchanger=new Exchanger<>();


        public TGetCourse(Exchanger<ArrayList<CCourse>> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                exchanger.exchange(db.getCoursedata());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static class TGetRandom extends Thread{
        Exchanger<ArrayList<CRandom>>exchanger=new Exchanger<>();
        int user_id;

        public TGetRandom(Exchanger<ArrayList<CRandom>> exchanger,int user_id) {
            this.exchanger = exchanger;
            this.user_id=user_id;
        }

        @Override
        public void run() {
            try {
                exchanger.exchange(db.getRandomdata(user_id));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class TLogin extends Thread{
        Exchanger<String>exchanger=new Exchanger<>();
        String login;
        String password;

        public TLogin(Exchanger<String> exchanger, String login, String password) {
            this.exchanger = exchanger;
            this.login = login;
            this.password = password;
        }

        @Override
        public void run() {
            try {
                exchanger.exchange(db.login(login,password));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static class TReg extends Thread{
        Exchanger<Boolean>exchanger=new Exchanger<>();
        CUsers users;

        public TReg(Exchanger<Boolean> exchanger, CUsers users) {
            this.exchanger = exchanger;
            this.users = users;
        }

        @Override
        public void run() {
            try {
                exchanger.exchange(db.SET(users));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

public static class TSetrec extends Thread{
    int user_id,random_id;
    String title;
    public TSetrec(String title, int user_id, int random_id) {
        this.user_id = user_id;
        this.random_id = random_id;
        this.title = title;
    }
    @Override
    public void run() {

        db.Srec(title,user_id,random_id);
    }


}
public static class TUpdate extends Thread {
        CUsers users;

    public TUpdate(CUsers users) {
        this.users = users;
    }

    @Override
    public void run() {
      db.UpdateUserdata(users);
    }
}

public static class Tnewpass extends Thread{
        String email;
        public Tnewpass(String email){
            this.email=email;
        }

    @Override
    public void run() {
        db.NewPassword(email);
    }
}


}
