package com.example.bikefix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val button1 = findViewById<Button>(R.id.button12)
        button1.setOnClickListener(){
            val intent= Intent(this@HomeActivity,BasicCheckActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dropdown,menu);
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId;
        if (id == R.id.home){
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
            return true
        }
        else if (id == R.id.forum){
            val intent= Intent(this@HomeActivity,ForumActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            val intent= Intent(this@HomeActivity,MapsActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}