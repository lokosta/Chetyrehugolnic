package com.example.user.chetyrehugolnic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public interface database {

    public CUsers getUsersdata(int id_u);
    public void UpdateUserdata(CUsers users);
    public void NewPassword(String email);
    public String login(String login, String password) throws JSONException;
    public Boolean setUserdata(CUsers use);
    public ArrayList<CCourse> getCoursedata() throws JSONException;
    public ArrayList<CEvent> getEventdata() throws JSONException;
    public JSONArray GET(String Title) throws JSONException;
    public ArrayList<CRandom> getRandomdata(int user_id) throws JSONException;

    public CRandom getRandomdata(String coloum, String arg);
    public CGroups getGroupsdata(String coloumn, String arg);
    public JSONObject GET(String title, String login, String password) throws JSONException;
    public boolean SET(CUsers users) throws JSONException;
    public JSONArray GET(String title, int user_id, String Table_Name) throws JSONException;
    public void Srec (String title,int user_id,int random_id);

}
