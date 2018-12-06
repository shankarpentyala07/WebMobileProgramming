package com.example.shankarpentyala.androidicp8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v)
    {
        EditText UserName = (EditText) findViewById(R.id.editText2);
        EditText Password1 =(EditText) findViewById(R.id.editText4);
        TextView Errortext = (TextView) findViewById(R.id.textView);
        String S_username = UserName.getText().toString();
        String S_pwd =Password1.getText().toString();
        if(S_username.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Enter User Name", Toast.LENGTH_SHORT).show();
        }
        else if (S_pwd.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
        }
        else if (!S_username.equals("LAB4"))
        {
            Toast.makeText(getApplicationContext(),"Invalid UserName",Toast.LENGTH_SHORT).show();
        }
        else if(!S_pwd.equals("LAB4"))
        {
            Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent redirect=new Intent(MainActivity.this,LoggedIn.class);
            startActivity(redirect);
        }

    }
}
