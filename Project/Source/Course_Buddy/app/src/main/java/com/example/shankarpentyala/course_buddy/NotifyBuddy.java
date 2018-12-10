package com.example.shankarpentyala.course_buddy;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class NotifyBuddy extends AppCompatActivity {
EditText title,texter;
    String Title,Texter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_buddy);
        Intent servintent = new Intent(getApplicationContext(),mynotifier.class);
        startService(servintent);
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        title = (EditText)findViewById(R.id.editText);
        texter = (EditText)findViewById(R.id.editText2);
    }
    void send(View view)
    {
        Title = title.getText().toString();
        Texter = texter.getText().toString();
        JSONObject js = new JSONObject();
        try {
            JSONObject j1 = new JSONObject();
            j1.put("title",Title);
            j1.put("text",Texter);
            j1.put("click_action","OPEN_ACTIVITY_1");
            js.put("data",j1);
            js.put("to","/topics/news");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "https://fcm.googleapis.com/fcm/send",js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(NotifyBuddy.this,"Notification sent to students",Toast.LENGTH_LONG).show();
                title.setText("");
                texter.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                params.put("Authorization", "key=AAAAfhZ0Wrc:APA91bGleP1femyfPZnuolVwg4yvw7g0G0-5RMIPrYYFej1Ka7NvEBvQcFzifRkifWeWI87cqRts2dUMD1VRXxOiYFAqlZZg6rU8DPi4n3rfV24-50vuCxmZeonLyAHJFAY_jQ0BsKzR");
                return params;
            }
        };
        requestQueue.add(request);
    }
}
