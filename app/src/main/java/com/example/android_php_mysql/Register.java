package com.example.android_php_mysql;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText edtName, edtPassword, edtContact, edtCountry;
    String name, password, contact, country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = (EditText) findViewById(R.id.edt_name);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtContact = (EditText) findViewById(R.id.edt_contact);
        edtCountry = (EditText) findViewById(R.id.edt_country);
    }

    public void regUser(View view){

        name = edtName.getText().toString();
        password = edtPassword.getText().toString();
        contact = edtContact.getText().toString();
        country = edtCountry.getText().toString();

        String method = "register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, name, password, contact, country);
        finish();

    }

}
