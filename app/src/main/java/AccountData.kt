class AccountData (name:String, lastName:String, streetName:String, streetNumber:Int, eMail:String, postalCode:String, cityName:String){
    var name:String? = null
    var lastName:String? = null
    var streetName:String? = null
    var streetNumber:Int? = null
    var flatNumber:Int? = null
    var eMail:String? = null
    var postalCode:String? = null
    var cityName:String? = null
    init {
        this.name = name
        this.lastName = lastName
        this.streetName = streetName
        this.streetNumber = streetNumber
        this.flatNumber = flatNumber
        this.eMail = eMail
        this.postalCode = postalCode
        this.cityName = cityName

    }



}