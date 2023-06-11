package com.dlap2023.apihpe2.network

import com.dlap2023.apihpe2.model.CharacterDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HpApi {
    //https://hp-api.onrender.com/api/characters/

    @GET("api/characters/{opt}")
    fun getCategoryHpCharacter(
        @Path("opt") opt: String?
    ): Call<CharacterDetail>

    @GET("api/characters/students/{id}")
    fun getHpCharacterStudentDetail(
        @Path("id") id: String?
    ): Call<CharacterDetail>

    @GET("api/characters/students/{id}")
    fun getHpCharacterStaffDetail(
        @Path("id") id: String?
    ): Call<CharacterDetail>
}