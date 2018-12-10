package com.example.shankarpentyala.course_buddy;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.sinch.android.rtc.ClientRegistration;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchClientListener;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;

import static android.content.ContentValues.TAG;

/**
 * Created by usgir on 4/28/2017.
 */

public class Sinchservice extends Service {
    SinchClient sinchClient;
    private final IBinder MySinchinterface = new Sinchinterface();
    String user;
    String user2;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public void start(String userId)
    {
        user = userId;
        final android.content.Context context = getApplicationContext();
        //Toast.makeText(context,"Got the Clent",Toast.LENGTH_SHORT).show();
        sinchClient = Sinch.getSinchClientBuilder().context(context)
                .applicationKey("e1a74bd4-100c-4185-9d20-54b2e7e0585a")
                .applicationSecret("vYI21CX/sUGU9GuMDcX+Jw==")
                .environmentHost("sandbox.sinch.com")
                .userId(user)
                .build();
        //Toast.makeText(context,"built the Clent",Toast.LENGTH_SHORT).show();
        sinchClient.setSupportCalling(true);
        //sinchClient.setSupportManagedPush(true);
        sinchClient.startListeningOnActiveConnection();
        sinchClient.addSinchClientListener(new SinchClientListener() {
            @Override
            public void onClientStarted(SinchClient sinchClient) {
                Toast.makeText(getApplicationContext(),"Started the Clent",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClientStopped(SinchClient sinchClient) {
                Toast.makeText(getApplicationContext(),"Stopped the Clent",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClientFailed(SinchClient sinchClient, SinchError sinchError) {
                Toast.makeText(context,"Failed to log the Clent",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRegistrationCredentialsRequired(SinchClient sinchClient, ClientRegistration clientRegistration) {
                Toast.makeText(context,"Failed to log the Clent",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLogMessage(int i, String s, String s1) {

            }
        });
        sinchClient.start();
        sinchClient.getCallClient().addCallClientListener(new SinchCallClientListener());
        Intent intent = new Intent(this,Outgoing.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public SinchClient call(String user)
    {
        user2=user;
        Toast.makeText(getApplicationContext(),"Calling user:"+user2,Toast.LENGTH_SHORT).show();
        return sinchClient;

    }
    @Override
    public IBinder onBind(Intent intent) {
        return MySinchinterface;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    public class Sinchinterface extends Binder
    {
        public void Startclient(String userid)
        {
            start(userid);
        }
        public SinchClient calluser(String Userid)
        {
            SinchClient sinchClient1 = call(Userid);
            return sinchClient1;
        }

    }
    private class SinchCallClientListener implements CallClientListener {

        @Override
        public void onIncomingCall(CallClient callClient, Call call)
        {
            Log.d(TAG, "Incoming call");
            Toast.makeText(getApplicationContext(),"Incoming Call Received",Toast.LENGTH_SHORT).show();
            call.answer();
            Toast.makeText(getApplicationContext(),"Answering call",Toast.LENGTH_SHORT).show();
            call.addCallListener(new SinchCallListener());
            Intent intent = new Intent(Sinchservice.this, Incoming.class);
            //intent.putExtra(CALL_ID, call.getCallId());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Sinchservice.this.startActivity(intent);

        }
    }
}
