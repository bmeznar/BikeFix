package com.example.bikefix;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.renderscript.ScriptGroup;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ForumActivity extends AppCompatActivity implements addForumDialog.addForumDialogListener {

    long lastForumID;

    DatabaseReference ref;

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText txtInput;

    boolean FirstTime=true;//zato, da se prikaz listviewa zgodi samo enkrat

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //listview
        ListView listView=(ListView)findViewById(R.id.listView);
        String[] items={};
        arrayList=new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,arrayList);
        listView.setAdapter(adapter);


        //prikaz naslovov v listView
        ref=FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(FirstTime==true){
                    long otroci=dataSnapshot.getChildrenCount();
                    otroci++;
                    for(int i=1;i<otroci;i++){
                        String header=dataSnapshot.child("forum"+i).child("Naslov").getValue().toString();
                        if(header==null){
                            break;
                        }
                        lastForumID=i;
                        //dodajanje v listview
                        arrayList.add(header);
                    }
                    FirstTime=false;
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //klik na item v listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.clear();
                openActivity2(position);
                finish();
            }
        });

        //dodajanje foruma
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }

    public void openDialog(){
        addForumDialog addforum=new addForumDialog();
        addforum.show(getSupportFragmentManager(),"example dialog");
    }


    DatabaseReference ref2=FirebaseDatabase.getInstance().getReference();

    @Override
    public void applyTexts(String naslov, String comment) {
        lastForumID++;
        arrayList.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("forum"+lastForumID);
        myRef.setValue("");
        DatabaseReference myRef3=database.getReference("forum"+lastForumID+"/Naslov");
        myRef3.setValue(naslov);
        DatabaseReference myRef2=database.getReference("forum"+lastForumID+"/comment1");
        myRef2.setValue(comment);

        //prikaze nov seznam
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long otroci=dataSnapshot.getChildrenCount();
                otroci++;
                for(int i=1;i<otroci;i++){
                    String header=dataSnapshot.child("forum"+i).child("Naslov").getValue().toString();
                    if(header==null){
                        break;
                    }
                    lastForumID=i;
                    //dodajanje v listview
                    arrayList.add(header);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openActivity2(int position){
        position++;
        int forumID=position;
        Intent intent=new Intent(this,ForumThreadActivity.class);
        intent.putExtra("forumID",forumID);
        startActivity(intent);
    }
}