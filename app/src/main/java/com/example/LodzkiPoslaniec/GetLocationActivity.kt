package com.example.LodzkiPoslaniec

import android.content.Intent
import android.icu.lang.UCharacter.getName
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.jetbrains.annotations.NotNull
import java.lang.Character.getName
import java.util.*
import kotlin.NullPointerException

class GetLocationActivity : FragmentActivity(), OnMapReadyCallback {
   // var mapView:SupportMapFragment? = null
    var mapsFragment:MapFragment? = null
    var doneButton:Button? = null
    var marker:Marker? = null
    private var databaseRef: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_location)
        initWidgets()
        setOnClickListeners()
    }
    fun initWidgets(){
       //mapView = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
       // mapsFragment = supportFragmentManager.findFragmentById(R.id.map) as MapFragment?
       // mapsFragment?.
        //mapView?.getMapAsync(this)
        mapsFragment = fragmentManager.findFragmentById(R.id.map) as MapFragment
        mapsFragment!!.getMapAsync(this)
        doneButton = findViewById(R.id.mapButton)
        databaseRef = FirebaseDatabase.getInstance().reference.child("PinLocation")
    }
    fun setOnClickListeners(){
        doneButton?.setOnClickListener(View.OnClickListener {it->
            var position:LatLng? = null
            var title:String? = null
            try {
                position = marker?.position as LatLng
            }
            catch (e:TypeCastException){
                Toast.makeText(this@GetLocationActivity,"Couldn't get pin's coordinates",Toast.LENGTH_SHORT).show()
            }
            try {
                 title = marker?.title
            }
            catch (e:TypeCastException){
                Toast.makeText(this@GetLocationActivity,"Couldn't get pin's address",Toast.LENGTH_SHORT).show()
            }
            println( "Position: $position, Address: $title")
            var pinLocation:PinLocation = PinLocation(position,title, EVENTTYPE.values()[intent.extras?.getInt(
                typeName)!!].name)
            //intent.putExtra(mapView.)
            databaseRef?.push()?.setValue(pinLocation)
            var intent: Intent = Intent(this@GetLocationActivity,InfrastructureActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onMapReady(p0: GoogleMap?) {
        val southwest = LatLng(51.69, 19.32)
        val northeast = LatLng(51.84, 19.63)
        val lodz = LatLng(51.76, 19.45)
        marker = p0?.addMarker(MarkerOptions().position(lodz).title("Pothole location").draggable(true))
        val bounds = LatLngBounds(southwest,northeast)
        p0?.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds,0))
        p0?.setOnMapLongClickListener {latLng ->
            p0?.clear()
            marker = p0?.addMarker(MarkerOptions().position(latLng).title("Pothole location").draggable(true))
            var geocoder: Geocoder = Geocoder(this, Locale.getDefault())
            var addresses = marker?.position?.longitude?.let {it->
                marker?.position?.latitude?.let { it1 ->
                    geocoder.getFromLocation(
                        it1,
                        it,1)
                }
            }
            val address: String? = addresses?.get(0)?.getAddressLine(0)
            val city: String? = addresses?.get(0)?.locality
            val country: String? = addresses?.get(0)?.countryName
            val postalCode = addresses?.get(0)?.postalCode
            marker?.title = "$address-$city-$postalCode"
            marker?.showInfoWindow()
            Toast.makeText(this,"Coordinates: ${marker?.position?.latitude}, ${marker?.position?.longitude}",Toast.LENGTH_SHORT).show()
        }
    }
}
