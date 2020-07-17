package com.example.LodzkiPoslaniec

import AccountData
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.BlendMode
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColor
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private var editTextName:EditText? = null
    private var editTextLastName:EditText? = null
    private var editTextStreetName:EditText? = null
    private var editTextStreetNumber:EditText? = null
    private var editTextFlatNumber:EditText? = null
    private var editTextPostalCode:EditText? = null
    private var editTextCityName:EditText? = null
    private var editTextEMail:EditText? = null
    private var editTextPassword1:EditText? = null
    private var editTextPassword2:EditText? = null
    private var buttonConfirm:Button? = null
    private var databaseRef: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initWidgets()

        onButtonConfirmClick()
    }
    fun initWidgets(){
        databaseRef = FirebaseDatabase.getInstance().reference.child("AccountData")
        editTextName = findViewById(R.id.editTextName)
        editTextLastName = findViewById(R.id.editTextLastName)
        editTextStreetName = findViewById(R.id.editTextStreet)
        editTextStreetNumber = findViewById(R.id.editTextStreetNumber)
        editTextFlatNumber = findViewById(R.id.editTextRegisterFlatNumber)
        editTextPostalCode = findViewById(R.id.editTextRegisterPostalCode)
        editTextCityName = findViewById(R.id.editTextRegisterCityName)
        editTextEMail = findViewById(R.id.editTextRegisterEmail)
        editTextPassword1 = findViewById(R.id.editTextRegisterPassword1)
        editTextPassword2 = findViewById(R.id.editTextRegisterPassword2)
        buttonConfirm = findViewById(R.id.buttonRegisterConfirm)
    }
    fun onButtonConfirmClick(){
        buttonConfirm?.setOnClickListener(View.OnClickListener {
            var howManyEditTextsCorrect:Int = 0
            if(editTextName?.text.toString() == ""){
                //editTextName?.background?.colorFilter =
                editTextName?.setBackgroundResource(R.drawable.edit_text_background_red)
            }
            else{
                editTextName?.setBackgroundResource(R.drawable.edit_text_background_normal)
                ++howManyEditTextsCorrect
            }
            if(editTextLastName?.text.toString() == ""){
                editTextLastName?.setBackgroundResource(R.drawable.edit_text_background_red)
            }
            else{
                editTextLastName?.setBackgroundResource(R.drawable.edit_text_background_normal)
                ++howManyEditTextsCorrect
            }
            if(editTextStreetName?.text.toString() == ""){
                editTextStreetName?.setBackgroundResource(R.drawable.edit_text_background_red)
            }
            else{
                editTextStreetName?.setBackgroundResource(R.drawable.edit_text_background_normal)
                ++howManyEditTextsCorrect
            }
            if(editTextStreetNumber?.text.toString() == ""){
                editTextStreetNumber?.setBackgroundResource(R.drawable.edit_text_background_red)
                //editTextStreetNumber?.setHintTextColor()
            }
            else {
                editTextStreetNumber?.setBackgroundResource(R.drawable.edit_text_background_normal)
                ++howManyEditTextsCorrect
            }
            if(editTextFlatNumber?.text.toString() == ""){
                editTextFlatNumber?.setBackgroundResource(R.drawable.edit_text_background_red)
            }
            else {
                editTextFlatNumber?.setBackgroundResource(R.drawable.edit_text_background_normal)
                ++howManyEditTextsCorrect
            }
            if(editTextCityName?.text.toString() == ""){
                editTextCityName?.setBackgroundResource(R.drawable.edit_text_background_red)
            }
            else{
                editTextCityName?.setBackgroundResource(R.drawable.edit_text_background_normal)
                ++howManyEditTextsCorrect
            }
            if (editTextEMail?.text.toString() == ""){
                editTextEMail?.setBackgroundResource(R.drawable.edit_text_background_red)
            }
            else{
                editTextEMail?.setBackgroundResource(R.drawable.edit_text_background_normal)
                ++howManyEditTextsCorrect
            }
            if(editTextPostalCode?.text.toString() == ""){
                editTextPostalCode?.setBackgroundResource(R.drawable.edit_text_background_red)
            }
            else {
                editTextPostalCode?.setBackgroundResource(R.drawable.edit_text_background_normal)
                ++howManyEditTextsCorrect
            }
            if(howManyEditTextsCorrect == 8){
                val name = editTextName?.text.toString().capitalize().trim()
                val lastName = editTextLastName?.text.toString().capitalize().trim()
                val streetName = editTextStreetName?.text.toString().capitalize().trim()
                val streetNumber = editTextStreetNumber?.text.toString().toInt()
                val flatNumber = editTextFlatNumber?.text.toString().toInt()
                val eMail = editTextEMail?.text.toString()
                val postalCode = editTextPostalCode?.text.toString()
                val cityName = editTextCityName?.text.toString().capitalize().trim()
                val dataToSend = AccountData(name, lastName,streetName,streetNumber,eMail,postalCode,cityName)
                databaseRef?.push()?.setValue(dataToSend)
                var intent: Intent = Intent(this@RegisterActivity,HomeActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,getString(R.string.marked_fields_required),Toast.LENGTH_SHORT).show()
            }
        })
    }
}
