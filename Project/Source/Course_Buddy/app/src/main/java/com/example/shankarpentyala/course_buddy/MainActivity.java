package com.example.shankarpentyala.course_buddy;

import android.content.Intent;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText UserName;
    EditText Password;
    Button Login,Signups;
    TextView textView, textView3;
    // TextView Errortext = (TextView) findViewById(R.id.text_Error);
    FirebaseAuth author;
    FirebaseAuth.AuthStateListener listener;
    FirebaseUser user;
    String emailid , Pwd , sso;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Login");
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        author = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            }
        };
        author.addAuthStateListener(listener);
        UserName = (EditText) findViewById(R.id.input_UserName);
        Password = (EditText) findViewById(R.id.input_password);
        Login = (Button) findViewById(R.id.Login);
        Signups = (Button)findViewById(R.id.Signup);
        textView = (TextView) findViewById(R.id.textView);
        //textView3 = (TextView) findViewById(R.id.textView3);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Avenir Roman.otf");
        Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/AvenirLTStd-Heavy.otf");
        UserName.setTypeface(font);
        Password.setTypeface(font);
        Login.setTypeface(font1);
        textView.setTypeface(font1);
        //textView3.setTypeface(font1);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sso = UserName.getText().toString();
                emailid = sso+"@mail.umkc.edu";
                Pwd = Password.getText().toString();
                if (emailid.isEmpty()) {
                    UserName.setError("Enter Email_id");
                    //Toast.makeText(getApplicationContext(), "Enter User Name", Toast.LENGTH_SHORT).show();
                } else if (Pwd.isEmpty()) {
                    Password.setError("Enter Password");
                    //Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    author.signInWithEmailAndPassword(emailid, Pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                            b.setCancelable(true);
                            b.setTitle("Success");
                            b.setMessage("Successfully Signed in");
                            b.show();
                            verify();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                            b.setCancelable(true);
                            b.setTitle("Failed");
                            b.setMessage("Invalid SSO/password");
                            b.show();
                        }
                    });
                }
            }
        });
        Signups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentsignup = new Intent(MainActivity.this,Signup.class);
                startActivity(intentsignup);
            }
        });
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    public void verify()
    {
        user = author.getCurrentUser();
        boolverify();
    }
    public void boolverify()
    {
        if (user.isEmailVerified()==true)
        {
            if (!checkBox.isChecked()) {
                Intent intent = new Intent(MainActivity.this, Courses.class);
                intent.putExtra("sso",sso);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(MainActivity.this,OtherUser.class);
                intent.putExtra("sso",sso);
                startActivity(intent);
            }
        }
        if (user.isEmailVerified()==false)
        {
            Toast.makeText(getApplicationContext(),"Verification Incomplete\n you need to verify your email",Toast.LENGTH_LONG).show();
        }
    }
}






/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/