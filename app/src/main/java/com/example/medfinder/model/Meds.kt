package com.example.medfinder.model

import com.google.gson.annotations.SerializedName




data class MedObject (

    @SerializedName("range"          ) var range          : String?                      = null,
    @SerializedName("majorDimension" ) var majorDimension : String?                      = null,
    @SerializedName("values"         ) var values         : ArrayList<ArrayList<String>> = arrayListOf()
)
data class Meds(
    @SerializedName("Med_Name"     ) var MedName     : String? = null,
    @SerializedName("Med_Price"    ) var MedPrice    : String? = null,
    @SerializedName("Med_Apteka"   ) var MedApteka   : String? = null,
    @SerializedName("Med_Address"  ) var MedAddress  : String? = null,
    @SerializedName("Med_Recept"   ) var MedRecept   : String? = null,
    @SerializedName("Med_analogue" ) var MedAnalogue : String? = null,
    @SerializedName("Med_Category" ) var MedCategory : String? = null,
    @SerializedName("Med_Id"       ) var MedId       : String? = null
)

