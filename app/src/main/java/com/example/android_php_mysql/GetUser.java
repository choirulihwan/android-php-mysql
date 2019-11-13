package com.example.android_php_mysql;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetUser extends AppCompatActivity {

    String str_json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user);

        Button btnGetJson = findViewById(R.id.btnGetJson);
        btnGetJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJson(v);
            }
        });

        Button btnParse = findViewById(R.id.btnParse);
        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseJson(v);
            }
        });
    }

    public void getJson(View view){

        new GetUserBackground().execute();
    }


    public void parseJson(View view){
        Intent intent = new Intent(GetUser.this, DisplayListView.class);
        intent.putExtra("json_data", str_json);
        startActivity(intent);
    }

    class GetUserBackground extends AsyncTask<Void, Void, String> {

        String jsonUrl = "http://192.168.10.8/project/getuserjson.php";
        String json_string;

        public GetUserBackground(){
            super();
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(jsonUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //@Override
        protected void onPostExecute(String result) {
            TextView textView = (TextView) findViewById(R.id.txtJson);
            textView.setText(result);
            str_json = result;
            
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
