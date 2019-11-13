package com.example.android_php_mysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DisplayListView extends AppCompatActivity {

    private String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;

    String name, email, contact, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);

        contactAdapter = new ContactAdapter(this, R.layout.row_layout);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(contactAdapter);

        json_string = getIntent().getExtras().getString("json_data");

        try {
            jsonObject = new JSONObject(json_string);
            int i = 0;
            jsonArray = jsonObject.getJSONArray("response");
            while (i < jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(i);
                name = jo.getString("name");
                email = jo.getString("country");
                contact = jo.getString("contact");
                password = jo.getString("password");

                Contact objContact = new Contact(name, email, contact, password);
                contactAdapter.add(objContact);
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
