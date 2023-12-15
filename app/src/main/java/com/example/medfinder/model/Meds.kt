package com.example.medfinder.model

import com.google.gson.annotations.SerializedName
data class MedObject(
    @SerializedName("range")
    var range: String,
    @SerializedName("majorDimension")
    var majorDimension: String,
    @SerializedName("values")
    var values: ArrayList<Meds>
)
data class Meds(
    @SerializedName("Med_Name")
    var Med_Name: String,
    @SerializedName("Med_Price")
    var Med_Price: String,
    @SerializedName("Med_Apteka")
    var Med_Apteka: String,
    @SerializedName("Med_Address")
    var Med_Address: String,
    @SerializedName("Med_Receipt")
    var Med_Receipt: String,
    @SerializedName("Med_analogue")
    var Med_analogue: String,
    @SerializedName("Med_Category")
    var Med_Category: String,
    @SerializedName("Med_Id")
    var Med_Id: Int
)

