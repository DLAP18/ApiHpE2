package com.dlap2023.apihpe2.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    var id: String?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("actor")
    var actor: String?,

    @SerializedName("image")
    var image: String?
)
