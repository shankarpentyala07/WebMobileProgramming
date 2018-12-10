package com.example.shankarpentyala.course_buddy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CallBuddy extends AppCompatActivity {
    String user;
    Sinchservice.Sinchinterface sinchinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_buddy);
    }
    public void Onlogin(View view)
    {
        EditText edittext = (EditText)findViewById(R.id.calleditText);
        user = edittext.getText().toString();
        Intent intent = new Intent(this,Sinchservice.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);

    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            sinchinterface = (Sinchservice.Sinchinterface)service;
            sinchinterface.Startclient(user);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
