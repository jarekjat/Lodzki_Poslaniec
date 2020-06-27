package com.example.LodzkiPoslaniec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {
    var buttonRegister: Button? = null
    var buttonInfrastructure: Button? = null
    var buttonGreenery: Button? = null
    var buttonMPK: Button? = null
    var buttonOthers: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initWidgets()
        setOnClickListener()
    }
    fun initWidgets(): Unit{
        buttonRegister = findViewById(R.id.buttonRegister)
        buttonOthers = findViewById(R.id.buttonOthers)
        buttonGreenery = findViewById(R.id.buttonGreenery)
        buttonInfrastructure = findViewById(R.id.buttonInfrastructure)
    }
    fun setOnClickListener(){
        buttonInfrastructure?.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@HomeActivity, InfrastructureActivity::class.java)
            startActivity(intent)
        })
        buttonRegister?.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this@HomeActivity,RegisterActivity::class.java)
            startActivity(intent)
        })
        buttonInfrastructure?.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this@HomeActivity,InfrastructureActivity::class.java)
            startActivity(intent)
        })
        buttonMPK?.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this@HomeActivity,MPKActivity::class.java)
            startActivity(intent)
        })
    }
}
