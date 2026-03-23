package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.model.PersonModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PersonService {
    // Implementação da chamada de login

    @POST("Authentication/Login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<PersonModel> {

        return TODO("Provide the return value")
    }

    @POST("Authentication/Create")
    @FormUrlEncoded
    suspend fun create(
        @Field("email") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password") receivenews: String
    ): Response<PersonModel> {

        return TODO("Provide the return value")
    }
}