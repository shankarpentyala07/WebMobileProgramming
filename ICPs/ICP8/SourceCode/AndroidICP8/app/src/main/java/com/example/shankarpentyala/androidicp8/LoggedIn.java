package com.example.shankarpentyala.androidicp8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
    }


    public void logout(View v)
    {
        Intent redirect=new Intent(LoggedIn.this,MainActivity.class);
        startActivity(redirect);
    }


}
