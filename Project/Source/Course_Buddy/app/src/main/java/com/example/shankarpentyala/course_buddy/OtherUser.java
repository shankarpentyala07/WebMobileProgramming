package com.example.shankarpentyala.course_buddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OtherUser extends AppCompatActivity {
String sso , Name , lowsso , whom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user);
        whom = "TA";
        sso = getIntent().getStringExtra("sso");
        lowsso = sso.toLowerCase();
        if (lowsso.equals("mnpw3d"))
        {
            Name = "Marmik";
        }
        if (lowsso.equals("agp52"))
        {
            Name = "Arunit";
        }
        if (lowsso.equals("smgtb"))
        {
            Name = "Ramgopal";
        }
        if (lowsso.equals("sjhv6"))
        {
            Name = "Sidrah";
        }
        else
        {
            whom = "Tutor";
        }
    }
    void Chatroom(View view)
    {
        Intent intent = new Intent(OtherUser.this,ConnectBuddy.class);
        intent.putExtra("who",sso);
        intent.putExtra("whom",whom);
        intent.putExtra("TA",Name);
        startActivity(intent);
    }
    void notify(View view)
    {
        Intent intent = new Intent(OtherUser.this,NotifyBuddy.class);
        startActivity(intent);
    }
}
