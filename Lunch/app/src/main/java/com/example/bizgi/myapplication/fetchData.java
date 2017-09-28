package com.example.bizgi.myapplication;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.StringTokenizer;


/**
 * Created by Abhishek Panwar on 7/14/2017.
 */

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";


    Date cDate = new Date();
    String fDate = new SimpleDateFormat("dd.MM.yyyy").format(cDate);;

    String fDate2 = "30.09.2017";
    String hsonu = "ya";
    String  hh = "Yemek Yok!";



    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.myjson.com/bins/18d03l");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            for(int i =0 ;i <= JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed =  JO.get("tarih") + "\n\n\n\n"+
                         JO.get("yemek1") + "\n"+
                        JO.get("yemek2") + "\n"+
                         JO.get("yemek3") + "\n"+
                        JO.get("yemek4") + "\n"+
                        JO.get("yemek5")+ "\n\n"+
                        JO.get("kalori")+ "\n";

                if (JO.get("tarih").equals(fDate)){

                    dataParsed = dataParsed + singleParsed +"\n" ;
                    hsonu = "nein";
                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if (hsonu.equals("ya")){

            MainActivity.data.setText(this.hh);

        } else {
            MainActivity.data.setText(this.dataParsed);
        }

    }
}