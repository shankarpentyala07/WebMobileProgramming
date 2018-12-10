package com.example.shankarpentyala.course_buddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Courseforum extends AppCompatActivity {

    String[] chatting = {"Talk with Proffessor", "Talk with TA", "Disscussion Form", "More..."};
    String selectionitem , sso;
    int itemselected = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseforum);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("CourseForum");
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        Spinner chattingspinner = (Spinner) findViewById(R.id.chattingspinner);
        Button Logout = (Button) findViewById(R.id.Logout);
        Intent it = getIntent();
        sso = it.getStringExtra("sso");
        Button go = (Button) findViewById(R.id.go);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chatting);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        chattingspinner.setAdapter(adapter);
        chattingspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectionitem = parent.getItemAtPosition(position).toString();
                itemselected = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemselected==3) {
                    Toast.makeText(getApplicationContext(), "You selected " + selectionitem, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), ConnectBuddy.class);
                    i.putExtra("who",sso);
                    i.putExtra("whom","forum");
                    startActivity(i);
                }
                else if (itemselected==2)
                {
                    Toast.makeText(getApplicationContext(),"You selected "+selectionitem,Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),TalkTa.class);
                    i.putExtra("who",sso);
                    startActivity(i);
                }
                else if (itemselected==1)
                {
                    Toast.makeText(getApplicationContext(),"You selected "+selectionitem,Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),TalkWithProfessor.class);
                    i.putExtra("who",sso);
                    startActivity(i);
                }
                else if (itemselected==0)
                {
                    Toast.makeText(getApplicationContext(),"Plzz select an option",Toast.LENGTH_LONG).show();
                }
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
