package com.example.bizgi.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;


    public static TextView tar;

    public  static TextView y1;
    public static TextView y1k;
    public static TextView y2k;
    public static TextView y2;
    public static TextView y3;
    public static TextView y3k;
    public static TextView y4;
    public static TextView y4k;
    public static TextView y5;
    public static TextView y5k;
    public static TextView tk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tar = (TextView) findViewById(R.id.tar);

        y1 = (TextView) findViewById(R.id.y1);
        y1k = (TextView) findViewById(R.id.y1k);

        y2 = (TextView) findViewById(R.id.y2);
        y2k = (TextView) findViewById(R.id.y2k);

        y3 = (TextView) findViewById(R.id.y3);
        y3k = (TextView) findViewById(R.id.y3k);

        y4 = (TextView) findViewById(R.id.y4);
        y4k = (TextView) findViewById(R.id.y4k);

        y5 = (TextView) findViewById(R.id.y5);
        y5k = (TextView) findViewById(R.id.y5k);

        tk = (TextView) findViewById(R.id.tk);


       // fetchData process = new fetchData();
        new fetchData().execute();


    }



    private class fetchData extends AsyncTask<Void,Void,Void> {


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Veri Çekme Çabaları...");
            // Set progressdialog message
            mProgressDialog.setMessage("Yemek listesi alınıyor");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }



        Date cDate = new Date();
        String fDate = new SimpleDateFormat("dd.MM.yyyy").format(cDate);;

        String fDate2 = fDate + " C";
        String tar ="";
        Integer i = 0;
        String yemekler = "";

        String y1 ="";
        String y2 = "";
        String y3 = "";
        String y4 = "";
        String y5 = "";
        String tk = "";

        String y1k ="";
        String y2k = "";
        String y3k = "";
        String y4k = "";
        String y5k = "";
        String tkk = "";

        ArrayList<String> yemek = new ArrayList<String>();



        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc  = Jsoup.connect("http://bozok.edu.tr/yemek2.aspx").get();
                Elements he = doc.select("td[align=left]");
                Elements yemeklist = he.select("tr");

                for (Element ymk : yemeklist
                        ) {
                    if (!ymk.text().equals(null)){
                        yemek.add(ymk.text());
                    }

                }

                for ( i=0; i < yemek.size(); i++  ) {
                    if (yemek.get(i).equals(fDate2)){

                        tar = yemek.get(i).substring(0,10);

                        String y1_tmp = yemek.get(i+1);
                        String y2_tmp = yemek.get(i+2);
                        String y3_tmp = yemek.get(i+3);
                        String y4_tmp = yemek.get(i+4);
                        String y5_tmp = yemek.get(i+5);
                        String tk_tmp = yemek.get(i+6);

                        y1 = y1_tmp.substring(0, y1_tmp.length() -3);
                        y2 = y2_tmp.substring(0, y2_tmp.length() -3);
                        y3 = y3_tmp.substring(0, y3_tmp.length() -3);
                        y4 = y4_tmp.substring(0, y4_tmp.length() -3);
                        y5 = y5_tmp.substring(0, y5_tmp.length() -3);

                        y1k = y1_tmp.replaceAll("[^0-9]", "");;
                        y2k = y2_tmp.replaceAll("[^0-9]", "");;
                        y3k = y3_tmp.replaceAll("[^0-9]", "");;
                        y4k = y4_tmp.replaceAll("[^0-9]", "");;
                        y5k = y5_tmp.replaceAll("[^0-9]", "");;

                        tk = tk_tmp.replaceAll("[^0-9]", "");;

                        yemekler = " " + tar + "\n" + y1 + "\n" + y2 + "\n" + y3 +"\n" + y4 + "\n" + y5 + "\n\n\n"
                                + y1k + "\n" + y2k + "\n" + y3k + "\n" + y4k + "\n" + y5k + "\n" + tk;

                    }

                }


            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mProgressDialog.dismiss();

            if (!yemekler.equals("")){

                MainActivity.tar.setText(tar);

                MainActivity.y1.setText(y1);
                MainActivity.y1k.setText(y1k);
                MainActivity.y2.setText(y2);
                MainActivity.y2k.setText(y2k);
                MainActivity.y3.setText(y3);
                MainActivity.y3k.setText(y3k);
                MainActivity.y4.setText(y4);
                MainActivity.y4k.setText(y4k);
                MainActivity.y5.setText(y5);
                MainActivity.y5k.setText(y5k);
                MainActivity.tk.setText(tk);


            } else {
               setContentView(R.layout.hata);

            }






        }

    }






}

