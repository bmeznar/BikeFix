package com.example.bikefix;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ForumThreadActivity extends AppCompatActivity implements CommentDialog.CommentDialogListener {

    long lastCommentID;

    boolean prvic=true;

    DatabaseReference ref;

    private ArrayList<String> arrayList2;
    private ArrayAdapter<String> adapter2;
    private EditText txtinput2;

    private TextView textViewComment;

    int forumID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_thread);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get forumID from previous activity
        Intent intent=getIntent();
        forumID=intent.getIntExtra("forumID",0);

        ListView listView=(ListView)findViewById(R.id.scroll);
        String komentarji[]={};
        arrayList2=new ArrayList<>(Arrays.asList(komentarji));
        adapter2=new ArrayAdapter<String>(this,R.layout.comment_items,R.id.txtinput2,arrayList2);
        listView.setAdapter(adapter2);


        ref=FirebaseDatabase.getInstance().getReference().child("forum"+forumID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String header=dataSnapshot.child("Naslov").getValue().toString();
                TextView textView=(TextView)findViewById(R.id.textView3);
                textView.setText(header);
                //String comment=dataSnapshot.child("comment1").getValue().toString();
                if(prvic==true){
                    long otroci=dataSnapshot.getChildrenCount();
                    for(int i=1;i<otroci;i++){
                        String comment=dataSnapshot.child("comment"+i).getValue().toString();
                        lastCommentID=i;
                        //dodajanje v listview
                        arrayList2.add(comment);
                    }
                    prvic=false;
                }
                adapter2.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        Button btnBack=(Button)findViewById(R.id.button3);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBack();
                finish();
            }
        });
    }






    @Override
    public void onBackPressed() {
        arrayList2.clear();
        openBack();
        finish();
    }

    public void openBack(){
        Intent intent=new Intent(this,ForumActivity.class);
        startActivity(intent);
    }

    public void openDialog(){
        CommentDialog commentDialog=new CommentDialog();
        commentDialog.show(getSupportFragmentManager(),"Add comment");
    }

    @Override
    public void applyText(String comment) {
        lastCommentID++;
        arrayList2.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("forum"+forumID+"/comment"+lastCommentID);
        myRef.setValue(comment);
        ref=FirebaseDatabase.getInstance().getReference().child("forum"+forumID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String header=dataSnapshot.child("Naslov").getValue().toString();
                TextView textView=(TextView)findViewById(R.id.textView3);
                textView.setText(header);
                    long otroci=dataSnapshot.getChildrenCount();
                    for(int i=1;i<otroci;i++){
                        String comment=dataSnapshot.child("comment"+i).getValue().toString();
                        lastCommentID=i;
                        //dodajanje v listview
                        arrayList2.add(comment);
                    }
                adapter2.notifyDataSetChanged();}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}