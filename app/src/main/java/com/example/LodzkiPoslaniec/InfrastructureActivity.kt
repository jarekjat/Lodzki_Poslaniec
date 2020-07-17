package com.example.LodzkiPoslaniec

import android.Manifest
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
enum class EVENTTYPE{
    POTHOLE,
    DAMAGED_INFRASTRUCTURE,
    CLEANING_REQUEST,
    EMPTY;
    fun getName(number:Int){
        //var name: String? = null
            name.toLowerCase().capitalize()

    }
}
const val typeName:String = "type"
class InfrastructureActivity : AppCompatActivity() {

    private var buttonCall: Button? = null
    private var buttonReportAPotHole: Button? = null
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
        buttonReportAPotHole = findViewById(R.id.buttonInfrastructureReportAPotHole)
    }
    fun setOnClickListener(){
        buttonReportAPotHole?.setOnClickListener(View.OnClickListener {
            val reportIntent = Intent(this,GetLocationActivity::class.java)
            reportIntent.putExtra(typeName,EVENTTYPE.POTHOLE)
            startActivity(reportIntent)
        })
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
                .setTitle(R.string.call_permission_request_title)
                .setMessage(R.string.call_permission_request)
                .setPositiveButton("OK") { dialog, whichButton->
                    ActivityCompat.requestPermissions(this,permissionArray,CALL_REQUEST_CODE)
                }
                .setNegativeButton(R.string.cancel){dialog,whichButton->
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
