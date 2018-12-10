package com.example.shankarpentyala.course_buddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TalkWithProfessor extends AppCompatActivity {
String sso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_with_professor);
        sso = getIntent().getStringExtra("who");
    }
    void talk(View view)
    {
        Intent intent = new Intent(TalkWithProfessor.this,ConnectBuddy.class);
        intent.putExtra("who",sso);
        intent.putExtra("whom","Tutor");
        startActivity(intent);
    }
    void call(View view)
    {
        Intent intent = new Intent(TalkWithProfessor.this,CallBuddy.class);
        startActivity(intent);
    }
}
