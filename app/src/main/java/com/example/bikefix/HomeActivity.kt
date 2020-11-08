package com.example.bikefix

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val button1 = findViewById<Button>(R.id.button12)
        button1.setOnClickListener(){
            val intent= Intent(this@HomeActivity,BasicCheckActivity::class.java)
            startActivity(intent)
        }
        val button2 = findViewById<Button>(R.id.button11)
        button2.setOnClickListener(){
            val intent= Intent(this@HomeActivity,BasicCheckActivity::class.java)
            startActivity(intent)
        }
        val button3 = findViewById<Button>(R.id.button10)
        button3.setOnClickListener(){
            val intent= Intent(this@HomeActivity,BasicCheckActivity::class.java)
            startActivity(intent)
        }
        val button4 = findViewById<Button>(R.id.button9)
        button4.setOnClickListener(){
            val intent= Intent(this@HomeActivity,BasicCheckActivity::class.java)
            startActivity(intent)
        }
        val button5 = findViewById<Button>(R.id.button8)
        button5.setOnClickListener(){
            val intent= Intent(this@HomeActivity,BasicCheckActivity::class.java)
            startActivity(intent)
        }
        val button6 = findViewById<Button>(R.id.button7)
        button6.setOnClickListener(){
            val intent= Intent(this@HomeActivity,BasicCheckActivity::class.java)
            startActivity(intent)
        }
        val button7 = findViewById<Button>(R.id.button6)
        button7.setOnClickListener(){
            val intent= Intent(this@HomeActivity,BasicCheckActivity::class.java)
            startActivity(intent)
        }
        val button8 = findViewById<Button>(R.id.button5)
        button8.setOnClickListener(){
            val intent= Intent(this@HomeActivity,BasicCheckActivity::class.java)
            startActivity(intent)
        }
        val button9 = findViewById<Button>(R.id.button4)
        button9.setOnClickListener(){
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
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/search/bike+shop")
            )
            startActivity(browserIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}