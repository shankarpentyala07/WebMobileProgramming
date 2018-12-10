package com.example.shankarpentyala.course_buddy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConnectBuddy extends AppCompatActivity {
    StorageReference storageReference;
    FirebaseDatabase database;
    DatabaseReference reference;
    StorageReference childref;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> Keylist = new ArrayList<>();
    Girishadapter girishadapter;
    Uri uri2;
    Uri uri3;
    String k;
    String s5 = "user" , sso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_buddy);
        ListView listView = (ListView)findViewById(R.id.listofmsgs);
        girishadapter = new Girishadapter(getApplicationContext(),R.layout.listitem,R.id.listtext,list);
        listView.setAdapter(girishadapter);
        storageReference = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        Intent i2 = getIntent();
        sso = i2.getStringExtra("who");
        String whom = i2.getStringExtra("whom");
        if (whom.equals("forum")) {
            reference = database.getReference("image");
        }
        if (whom.equals("TA"))
        {
            String TA = i2.getStringExtra("TA");
            reference = database.getReference("TA/"+TA);
        }
        if (whom.equals("Tutor"))
        {
            reference = database.getReference("Tutor/Ylee");
        }
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key= dataSnapshot.getKey();
                String value = dataSnapshot.getValue().toString();
                Keylist.add(key);
                list.add(value);
                girishadapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.call) {
            Intent i = new Intent(getApplicationContext(),CallBuddy.class);
            startActivity(i);
            //Toast.makeText(getApplicationContext(),"Reached",Toast.LENGTH_LONG).show();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }
    void add(View view)
    {
        Calendar calendar = Calendar.getInstance();
        long date = calendar.getTimeInMillis();
        String d = Long.toString(date);
        EditText editText = (EditText)findViewById(R.id.connectmsg);
        String msg = editText.getText().toString();
        reference.child(d).setValue(msg);
        editText.setText("");
        editText.setHint("enter msg");
    }
    void addimg(View view) {
        Calendar calendar = Calendar.getInstance();
        long date = calendar.getTimeInMillis();
        k = Long.toString(date);
        childref = storageReference.child(k);
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }
    @Override
    protected void onActivityResult(int requestcode,int resultcode,Intent data)
    {
        uri2 = data.getData();
        childref.putFile(uri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
    void shower(View view)
    {
        storageReference.child(k).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String s=uri.toString();
                String kk = k.substring(0,10);
                reference.child(kk+"111").setValue(k);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
            }
        });
    }
    public class Girishadapter extends ArrayAdapter {
        Context c;
        public Girishadapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List objects) {
            super(context, resource, textViewResourceId, objects);
            c = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.listitem,(ViewGroup)findViewById(R.id.layy),false);
            final TextView textView = (TextView)view.findViewById(R.id.listtext);
            textView.setText(sso+" : "+list.get(position));
            String sss = Keylist.get(position).substring(10,13);
            final ImageView imageView = (ImageView)view.findViewById(R.id.listimage);
            if (sss.equals("111"))
            {
                imageView.setVisibility(View.VISIBLE);
                s5 = list.get(position);
                textView.setText(sso + " : ");
                storageReference.child(s5).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.with(getApplicationContext()).load(uri).into(imageView);
                        //Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
            return view;
        }
    }
}
