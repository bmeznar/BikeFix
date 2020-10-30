package com.example.bikefix;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BasicCheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_check);

        Button back=(Button)findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               Intent intent=new Intent(v.getContext(),HomeActivity.class);
               startActivityForResult(intent,0);
           }
        });

        Button video=(Button)findViewById(R.id.video);
        video.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(v.getContext(),VideoActivity.class);
                startActivityForResult(intent,0);
            }
        });

        Button tools=(Button)findViewById(R.id.button2);
        tools .setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openDialog();
            }
        });
    }

    public void openDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Tools needed");
        alert.setMessage("- Set of hex keys\n- Set of allan wrenches\n- Multitool");

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Your action here
            }
        });


        alert.show();
    }
}