package com.example.LodzkiPoslaniec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    val editTextName:EditText? = null
    val editTextLastName:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}
