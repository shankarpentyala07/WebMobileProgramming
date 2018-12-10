package com.example.shankarpentyala.course_buddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TalkTa extends AppCompatActivity {
    ArrayList<String> list;
    ArrayAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_ta);
        Intent it = getIntent();
        final String sso = it.getStringExtra("who");
        list = new ArrayList<>();
        list.add("Marmik Patel\n mnpw3d");
        list.add("Arunit Gupta\n agp52");
        list.add("Ram gopal\n smgtb");
        list.add("Sidrah Junaid\n sjhv6");
        listView = (ListView)findViewById(R.id.l1);
        adapter = new ArrayAdapter(this,R.layout.listta,R.id.taname,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TalkTa.this,ConnectBuddy.class);
                intent.putExtra("who",sso);
                intent.putExtra("whom","TA");
                if (position==0)
                {
                    intent.putExtra("TA","Marmik");
                }
                else if (position==1)
                {
                    intent.putExtra("TA","Arunit");
                }
                else if (position==2)
                {
                    intent.putExtra("TA","Ramgopal");
                }
                else {
                    intent.putExtra("TA","Sidrah");
                }
                startActivity(intent);
            }
        });
    }
}
