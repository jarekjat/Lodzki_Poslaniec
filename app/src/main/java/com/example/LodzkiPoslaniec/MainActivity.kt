package com.example.LodzkiPoslaniec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
        var buttonRegister: Button? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonRegister = findViewById(R.id.buttonRegister)
        setOnClickListener()
    }
    fun setOnClickListener(){
        buttonRegister?.setOnClickListener(View.OnClickListener {
           val intent :Intent = Intent(this@MainActivity,RegisterActivity::class.java)
            startActivity(intent)
        })
    }
}
