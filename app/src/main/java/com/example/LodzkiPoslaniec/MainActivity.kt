package com.example.LodzkiPoslaniec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager

class MainActivity : AppCompatActivity() {

        private var splash_time_out: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //var handler: Handler = Handler()
        setContentView(R.layout.activity_main)
        Handler().postDelayed(Runnable {

                var homeIntent:Intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(homeIntent)
                finish()
             },splash_time_out)

    }

}
