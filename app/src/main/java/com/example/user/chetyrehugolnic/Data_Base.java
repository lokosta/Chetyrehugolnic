package com.example.user.chetyrehugolnic;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sokol on 13.08.2018.
 */

public class Data_Base implements database {
    private Data_Base(){}
    static private Data_Base me;
    public static Data_Base instance(){
        if(me==null){
            me=new Data_Base();
        }
        return me;
    }
    String query="http://192.168.1.69";

    @Override
    public CUsers getUsersdata(int idu) {
      return null;

    }

    @Override
    public void UpdateUserdata(CUsers users) {
        JSONObject json;
        StringBuffer buffer=new StringBuffer();
        HttpURLConnection urlConnection=null;
        BufferedReader reader=null;
        try{
            URL url=new URL(query);
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
            urlConnection.setDoOutput(true);//Для использования POST
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");//отправка данных метод POST
            urlConnection.connect();

            json=new JSONObject();
            json.put("Method","UPDATE");//url адрес принимает значение
            json.put("user_id",users.getUser_ID());
            json.put("group_id",users.getGroup_ID());
            json.put("email",users.getEmail());
            json.put("password",users.getPassword());
            json.put("lastname",users.getLastName());
            json.put("firstname",users.getFirstname());
            json.put("info",users.getInfo());

            OutputStream outputstream=urlConnection.getOutputStream(); //посылаем данные(метод "POST")
            outputstream.write(json.toString().getBytes("UTF-8"));
            outputstream.close();

            InputStream inputstream=urlConnection.getInputStream();//получаем данные

            reader=new BufferedReader(new InputStreamReader(inputstream));//После подключение происходит считывание со входного потока

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }


        //отправляет строчку таблицы в базу данных на сервер

    }

    @Override
    public void NewPassword(String email) {

        JSONObject json;
        StringBuffer buffer=new StringBuffer();
        HttpURLConnection urlConnection=null;
        BufferedReader reader=null;
        try{
            URL url=new URL(query);
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
            urlConnection.setDoOutput(true);//Для использования POST
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");//отправка данных метод POST
            urlConnection.connect();

            json=new JSONObject();
            json.put("Method","NewPassword");//url адрес принимает значение
            json.put("email",email);


            OutputStream outputstream=urlConnection.getOutputStream(); //посылаем данные(метод "POST")
            outputstream.write(json.toString().getBytes("UTF-8"));
            outputstream.close();

            InputStream inputstream=urlConnection.getInputStream();//получаем данные

            reader=new BufferedReader(new InputStreamReader(inputstream));//После подключение происходит считывание со входного потока

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }

    }


    @Override
    public String login(String Login, String password) throws JSONException {
        //JSONArray jsonarray=GET("users",login,password);
        JSONObject js=GET("users",Login,password);
        String answer=js.getString("answer");
        if(answer.equals("ok")){
        login.userx=new CUsers(js.getInt("user_id"),js.getInt("group_id"),js.getString("email"),js.getString("_password_"),
                js.getString("lastname"),js.getString("firstname"),js.getString("info"));}//решить эту проблему!!!!!

        return answer;
    }

    @Override
    public Boolean setUserdata(CUsers use) {


        return null;
    }

    @Override
    public ArrayList<CCourse> getCoursedata() throws JSONException {
        JSONArray jsonArray=GET("courses");
        ArrayList<CCourse> sName=new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            JSONObject obj=jsonArray.getJSONObject(i);
            CCourse el=new CCourse(obj.getInt("user_id"),obj.getInt("course_id"),obj.getString("lastname")+obj.getString("firstname"),
                    obj.getString("start_time"),obj.getString("finish_time"),obj.getString("title"),obj.getString("description"),obj.getBoolean("zapis"),"topics");
            sName.add(el);
            //метод работы с БД,должен возвращать ArrayList со списком данных
        }return sName;
    }
    @Override
    public ArrayList<CEvent> getEventdata() throws JSONException {
        JSONArray jsonArray=GET("events");
        ArrayList<CEvent> sName=new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            JSONObject obj=jsonArray.getJSONObject(i);
            CEvent el=new CEvent(obj.getInt("user_id"),obj.getInt("event_id"),obj.getString("lastname")+obj.getString("firstname"),
                    obj.getString("start_time"),obj.getString("finish_time"),obj.getString("title"),obj.getString("description"),obj.getBoolean("zapis"));
            sName.add(el);
            //метод работы с БД,должен возвращать ArrayList со списком данных
        }return sName;
    }

    @Override
    public ArrayList<CRandom> getRandomdata(int user_id) throws JSONException {
        ArrayList<CRandom>cRandoms=new ArrayList<>();
        JSONArray jsonArray2=GET("records",user_id,"events");
        for (int i=0;i<jsonArray2.length();i++){
            JSONObject obj=jsonArray2.getJSONObject(i);
            CRandom el=new CEvent(obj.getInt("user_id"),obj.getInt("event_id"),
                    obj.getString("lastname")+obj.getString("firstname"),obj.getString("start_time"),
                    obj.getString("finish_time"),obj.getString("title"),obj.getString("description"),true);
            cRandoms.add(el);
        }
        JSONArray jsonArray=GET("records",user_id,"courses");//отправка user_id пользователя отправлющего запрос
        for (int i=0;i<jsonArray.length();i++){
            JSONObject obj=jsonArray.getJSONObject(i);
            CRandom el=new CCourse(obj.getInt("user_id"),obj.getInt("course_id"),
                    obj.getString("lastname")+obj.getString("firstname"),obj.getString("start_time"),
                    obj.getString("finish_time"),obj.getString("title"),obj.getString("description"),true,"topics");//description
            cRandoms.add(el);
        }

        return cRandoms;
    }

    @Override
    public CRandom getRandomdata(String coloum, String arg) {
        return null;
    }

  

    @Override
    public CGroups getGroupsdata(String coloumn, String arg) {
        return null;
    }


    @Override
    public JSONArray GET(String title) throws JSONException {

        JSONObject json,js;
        JSONArray jsO;
        StringBuffer buffer=new StringBuffer();//?
        HttpURLConnection urlConnection=null;
        BufferedReader reader=null;
        //получаем данные с внешнего ресурса
        try{
            URL url=new URL(query);
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
            urlConnection.setDoOutput(true);//Для использования POST
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");//отправка данных метод POST
            urlConnection.connect();

            json=new JSONObject();
            json.put("Method","GET");//url адрес принимает значение
            json.put("Table_Name",title);



            OutputStream outputstream=urlConnection.getOutputStream(); //посылаем данные(метод "POST")
            outputstream.write(json.toString().getBytes("UTF-8"));//?
            outputstream.close();

            InputStream inputstream=urlConnection.getInputStream();//получаем данные

            reader=new BufferedReader(new InputStreamReader(inputstream));//После подключение происходит считывание со входного потока

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }
        jsO=new JSONArray(buffer.toString());
        return jsO;

    }


    @Override
    public JSONObject GET(String title,String login,String password) throws JSONException {

        //login l=new login();
        JSONObject json,js;
        JSONArray jsO;
        StringBuffer buffer=new StringBuffer();
        HttpURLConnection urlConnection=null;
        BufferedReader reader=null;
        //получаем данные с внешнего ресурса
        try{
            URL url=new URL(query);
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
            urlConnection.setDoOutput(true);//Для использования POST
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");//отправка данных метод POST
            urlConnection.connect();

            json=new JSONObject();
            if ((login==null)&&(password==null))
            {json.put("Method","GET");//url адрес принимает значение
            json.put("Table_Name",title);}
            else { json.put("login",login);
            json.put("password",password);
            json.put("Method","GET");
            json.put("Table_Name",title);

            }
            OutputStream outputstream=urlConnection.getOutputStream(); //посылаем данные(метод "POST")
            outputstream.write(json.toString().getBytes("UTF-8"));//?
            outputstream.close();

            InputStream inputstream=urlConnection.getInputStream();//получаем данные

            reader=new BufferedReader(new InputStreamReader(inputstream));//После подключение происходит считывание со входного потока

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
            //Toast.makeText(l.context,"проблемы с соединением",Toast.LENGTH_SHORT).show();
        } finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }
        js=new JSONObject(buffer.toString());
        return js;

    }

    @Override
    public boolean SET(CUsers users) throws JSONException {
        JSONObject json;
        StringBuffer buffer=new StringBuffer();//?
        HttpURLConnection urlConnection=null;
        BufferedReader reader=null;
        try{
            URL url=new URL(query);
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
            urlConnection.setDoOutput(true);//Для использования POST
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");//отправка данных метод POST
            urlConnection.connect();

            json=new JSONObject();
            json.put("Method","SET");//url адрес принимает значение
            json.put("user_id",12);
            json.put("group_id",3);
            json.put("email",users.getEmail());
            json.put("password",users.getPassword());
            json.put("lastname",users.getLastName());
            json.put("firstname",users.getFirstname());
            json.put("info","info");

            OutputStream outputstream=urlConnection.getOutputStream(); //посылаем данные(метод "POST")
            outputstream.write(json.toString().getBytes("UTF-8"));
            outputstream.close();

            InputStream inputstream=urlConnection.getInputStream();//получаем данные

            reader=new BufferedReader(new InputStreamReader(inputstream));//После подключение происходит считывание со входного потока

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }
        JSONObject jsO= null;

            jsO = new JSONObject(buffer.toString());

        boolean b=jsO.getBoolean("znachenie");
        return b;


    }

    @Override
    public JSONArray GET(String title, int user_id,String Table_Name) throws JSONException {

            JSONObject json;
            JSONArray jsO;
            StringBuffer buffer = new StringBuffer();
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            //получаем данные с внешнего ресурса
            try {
                URL url = new URL(query);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
                urlConnection.setDoOutput(true);//Для использования POST
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("POST");//отправка данных метод POST
                urlConnection.connect();

                json = new JSONObject();

                json.put("user_id", user_id);
                json.put("Method", "Grec");
                json.put("Table_Name", Table_Name);

                OutputStream outputstream = urlConnection.getOutputStream(); //посылаем данные(метод "POST")
                outputstream.write(json.toString().getBytes("UTF-8"));
                outputstream.close();

                InputStream inputstream = urlConnection.getInputStream();//получаем данные

                reader = new BufferedReader(new InputStreamReader(inputstream));//После подключение происходит считывание со входного потока

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            jsO = new JSONArray(buffer.toString());
            Log.i("kvadrat",buffer.toString());
            return jsO;
        }

    @Override
    public void Srec(String title, int user_id, int random_id) {
        JSONObject json;
        StringBuffer buffer=new StringBuffer();
        HttpURLConnection urlConnection=null;
        BufferedReader reader=null;
        try{
            URL url=new URL(query);
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
            urlConnection.setDoOutput(true);//Для использования POST
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");//отправка данных метод POST
            urlConnection.connect();

            json=new JSONObject();
            json.put("Method","Srec");//url адрес принимает значение
            json.put("user_id",user_id);
            json.put("random_id",random_id);/////id
            json.put("Table_Name",title);
//            json.put("password",users.getPassword());
//            json.put("lastname",users.getLastName());
//            json.put("firstname",users.getFirstname());
//            json.put("info",users.getInfo());

            OutputStream outputstream=urlConnection.getOutputStream(); //посылаем данные(метод "POST")
            outputstream.write(json.toString().getBytes("UTF-8"));//?
            outputstream.close();

            InputStream inputstream=urlConnection.getInputStream();//получаем данные

            reader=new BufferedReader(new InputStreamReader(inputstream));//После подключение происходит считывание со входного потока

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }
    }
}

