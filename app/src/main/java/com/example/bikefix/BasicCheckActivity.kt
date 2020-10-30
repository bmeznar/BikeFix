package com.example.bikefix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BasicCheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_check)

        var btn=findViewById<Button>(R.id.button)
        btn.setOnClickListener(){
            val intent= Intent(this@BasicCheckActivity,HomeActivity::class.java)
            startActivity(intent)
        }

        var btn2=findViewById<Button>(R.id.video)
        btn2.setOnClickListener(){
            val intent= Intent(this@BasicCheckActivity,VideoActivity::class.java)
            startActivity(intent)
        }
    }

}