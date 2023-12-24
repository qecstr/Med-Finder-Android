package com.example.medfinder.model

import com.google.gson.annotations.SerializedName




data class MedObject (

    @SerializedName("range"          ) var range          : String?                      = null,
    @SerializedName("majorDimension" ) var majorDimension : String?                      = null,
    @SerializedName("values"         ) var values         : ArrayList<ArrayList<String>> = arrayListOf()
)
data class Meds(
    @SerializedName("Med_Name"     ) var MedName: String ,
    @SerializedName("Med_Price"    ) var MedPrice: String,
    @SerializedName("Med_Apteka"   ) var MedApteka: String,
    @SerializedName("Med_Address"  ) var MedAddress: String,
    @SerializedName("Med_Recept"   ) var MedRecept: String,
    @SerializedName("Med_analogue" ) var MedAnalogue: String,
    @SerializedName("Med_Category" ) var MedCategory: String,
    @SerializedName("Med_Id"       ) var MedId: String,
    @SerializedName("Med_Image")      var MedImage: String? = null
)

