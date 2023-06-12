package com.dlap2023.apihpe2.network

import com.dlap2023.apihpe2.model.CharacterDetail
import com.dlap2023.apihpe2.model.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface HpApi {
    //https://hp-api.onrender.com/api/characters/

    @GET("characters/{opt}")
    fun getHpCategoryCharacters(
        //@Url url: String?
        @Path("opt") opt: String?
    ): Call<ArrayList<Character>>

    @GET("character/{id}")
    fun getHpCharacterDetail(
        @Path("id") id: String?
    ): Call<CharacterDetail>
}