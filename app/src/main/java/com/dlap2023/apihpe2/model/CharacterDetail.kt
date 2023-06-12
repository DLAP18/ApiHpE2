package com.dlap2023.apihpe2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CharacterDetail(
    @SerializedName("name")
    var name: String?,

    @SerializedName("image")
    var image: String?,

    @SerializedName("species")
    var species: String?,

    @SerializedName("house")
    var house: String?,

    @SerializedName("dateOfBirth")
    var dateOfBirth: String?,

    @SerializedName("wizard")
    var wizard: Boolean,

    @SerializedName("ancestry")
    var ancestry: String?,

    @SerializedName("patronus")
    var patronus: String?
)