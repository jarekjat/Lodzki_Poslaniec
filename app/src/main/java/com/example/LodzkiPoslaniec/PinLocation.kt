package com.example.LodzkiPoslaniec

import com.google.android.gms.maps.model.LatLng

class PinLocation (coordinates: LatLng?, address:String?, type:String?){
    var coordinates:LatLng? = null
    var address:String? = null
    var type:String? = null
    init {
        this.address = address
        this.coordinates = coordinates
        this.type = type
    }
}