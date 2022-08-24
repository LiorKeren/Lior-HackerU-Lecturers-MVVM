package com.lior.lecturers.mvvm.data.api

import com.lior.lecturers.mvvm.data.model.Language
import com.lior.lecturers.mvvm.data.model.Lecturer
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/Lecturers/")
    suspend fun getLecturers(): Response<List<Lecturer>>

    @GET("/Languages/")
    suspend fun getLanguages(): Response<List<Language>>

//    @POST("/answers/")
//    suspend fun postAnswers(@Body languages: List<Language>)

}