package com.example.shankarpentyala.course_buddy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;

public class Outgoing extends AppCompatActivity {
    String user2;
    Sinchservice.Sinchinterface sinchinterface;
    SinchClient sinchClient;
    CallClient callClient;
    Call call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoing);
        Intent i = new Intent(this,Sinchservice.class);
        bindService(i,serviceConnection, Context.BIND_AUTO_CREATE);
    }
    public void oncall(View view)
    {
        EditText text = (EditText)findViewById(R.id.outfriend);
        user2 = text.getText().toString();
        sinchClient = sinchinterface.calluser(user2);
        String s = sinchClient.getLocalUserId();
        Toast.makeText(this,"Calling "+user2,Toast.LENGTH_SHORT).show();
        TextView textView = (TextView)findViewById(R.id.outtextView4);
        //Button button = (Button)findViewById(R.id.hang);
        callClient = sinchClient.getCallClient();
        call = callClient.callUser(user2);
        setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
        call.addCallListener(new SinchCallListener());
        textView.setVisibility(View.GONE);
        text.setVisibility(View.GONE);
        ImageButton imageButton = (ImageButton)findViewById(R.id.outimageButton2);
        imageButton.setVisibility(View.GONE);
        //button.setVisibility(View.VISIBLE);
        ImageView imageView = (ImageView)findViewById(R.id.outimageView2);
        imageView.setVisibility(View.VISIBLE);
        ImageButton imageButton1 = (ImageButton)findViewById(R.id.outimageButton3);
        imageButton1.setVisibility(View.VISIBLE);
        TextView textView1 = (TextView)findViewById(R.id.outtextView6);
        textView1.setText("Calling : "+user2);
        textView1.setVisibility(View.VISIBLE);
    }
    public void onhang(View view)
    {
        call.hangup();
        TextView textView = (TextView)findViewById(R.id.outtextView4);
        textView.setVisibility(View.VISIBLE);
        EditText text = (EditText)findViewById(R.id.outfriend);
        text.setVisibility(View.VISIBLE);
        ImageButton imageButton = (ImageButton)findViewById(R.id.outimageButton2);
        imageButton.setVisibility(View.VISIBLE);
        ImageView imageView = (ImageView)findViewById(R.id.outimageView2);
        imageView.setVisibility(View.GONE);
        ImageButton imageButton1 = (ImageButton)findViewById(R.id.outimageButton3);
        imageButton1.setVisibility(View.GONE);
        TextView textView1 = (TextView)findViewById(R.id.outtextView6);
        textView1.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(),"Call Ended",Toast.LENGTH_LONG).show();
    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            sinchinterface = (Sinchservice.Sinchinterface)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
