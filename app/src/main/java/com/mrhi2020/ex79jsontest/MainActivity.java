package com.mrhi2020.ex79jsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
    }

    public void clickBtn(View view) {
        //assets폴더의 파일을 가져오기 위해 창고관리자 소환
        AssetManager assetManager= getAssets();

        //assets/jsons/test.json 파일 읽기위한 InputStream
        try {
            InputStream is= assetManager.open("jsons/test.json");
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader reader= new BufferedReader(isr);

            StringBuffer buffer= new StringBuffer();
            String line= reader.readLine();
            while(line!=null){
                buffer.append(line+"\n");
                line= reader.readLine();
            }

            String jsonStr= buffer.toString();

            //읽어온 json문자열 확인
            tv.setText(jsonStr);

            //json문자열을 분석하여 Json객체로 변환
//            JSONObject jsonObject= new JSONObject(jsonStr);
//            String name= jsonObject.getString("name");
//            String message= jsonObject.getString("msg");
//
//            tv.setText(name+" : "+ message);

            //json문자열이 []로 시작하는 배열일때
            JSONArray jsonArray= new JSONArray(jsonStr);

            String s="";
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jo= jsonArray.getJSONObject(i);

                String name= jo.getString("name");
                String message= jo.getString("msg");
                JSONObject flag= jo.getJSONObject("flag");
                int aa= flag.getInt("aa");
                boolean bb= flag.getBoolean("bb");

                s += name +" :: "+ message + " - " +aa+","+ bb + "\n";
            }

            tv.setText(s);


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }
}