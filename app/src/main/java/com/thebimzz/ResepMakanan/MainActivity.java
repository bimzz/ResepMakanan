package com.thebimzz.ResepMakanan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    RecyclerView r1;
    String s1[],s2[];
    int imgResources []= {R.drawable.gudeg,R.drawable.mendoan,R.drawable.bakwan,R.drawable.gado,R.drawable.sate};

    myOwnAdapter ad;
    LinearLayoutManager ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r1 = (RecyclerView)findViewById(R.id.myRecycler);
        s1 = getResources().getStringArray(R.array.NamaResep);
        s2 = getResources().getStringArray(R.array.KET);
        TextView header = (TextView)findViewById(R.id.Header);
        JSONObject ResepMakan;
        JSONArray arrayResep ;



        try {
            ResepMakan = new JSONObject(loadJSONFromAsset());
            arrayResep = ResepMakan.getJSONArray("Resep");
            ad = new myOwnAdapter(this,s1,s2,imgResources,arrayResep);
            r1.setAdapter(ad);
            ly= new LinearLayoutManager(this);
            r1.setLayoutManager(ly);
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView header = (TextView)findViewById(R.id.Header);
        String txt= "HaWWo";
        String in = "{\"Header\":\"Menu Makan\"}";
        JSONObject ResepMakan;
        try {
            ResepMakan = new JSONObject(loadJSONFromAsset());

            txt = ResepMakan.getString("Header");

            header.setText(Html.fromHtml(txt) );
            loadJSONFromAsset();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String loadJSONFromAsset() {
        String json="";
        try {

            InputStream is = getAssets().open("resep.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;


    }

}
