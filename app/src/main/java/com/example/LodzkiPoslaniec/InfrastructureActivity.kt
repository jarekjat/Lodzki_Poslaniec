package com.example.LodzkiPoslaniec

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.lang.reflect.Array

class InfrastructureActivity : AppCompatActivity() {
    private var buttonCall: Button? = null
    private var CALL_REQUEST_CODE = 1
    val permissionArray = Array(1) {Manifest.permission.CALL_PHONE}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infrastructure)
        initWidgets()
        setOnClickListener()
    }
    fun initWidgets(){
        buttonCall = findViewById(R.id.buttonInfrastructureCall)
    }
    fun setOnClickListener(){
        buttonCall?.setOnClickListener(View.OnClickListener {
            val callIntent: Intent = Intent(Intent.ACTION_CALL)
            callIntent.setData(Uri.parse("tel:+48426384444"))
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
               // Toast.makeText(this,"Permission not granted",Toast.LENGTH_SHORT).show()
                requestCallPermission()
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    startActivity(callIntent)
                }

                //ActivityCompat.requestPermissions(this,permissionArray,CALL_REQUEST_CODE)
                //onRequestPermissionsResult(CALL_REQUEST_CODE,permissionArray,IntArray(1){1})
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //return@OnClickListener
            }
            else{
                startActivity(callIntent)
            }

        })
    }
    fun requestCallPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
            AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("Zgoda jest potrzebna, aby wykonać połączenie telefoniczne. Zostaniesz połączony z miejskim call center")
                .setPositiveButton("OK") { dialog, whichButton->
                    ActivityCompat.requestPermissions(this,permissionArray,CALL_REQUEST_CODE)
                }
                .setNegativeButton("Cancel"){dialog,whichButton->
                    dialog.dismiss()
                   // ActivityCompat.requestPermissions(this,permissionArray,CALL_REQUEST_CODE)
                }
                .create().show()


        }
        else{
            ActivityCompat.requestPermissions(this,permissionArray,CALL_REQUEST_CODE)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: kotlin.Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == CALL_REQUEST_CODE){
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission granted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
