package com.lior.lecturers.mvvm.data.api

import com.lior.lecturers.mvvm.data.model.Language
import com.lior.lecturers.mvvm.data.model.Lecturer
import retrofit2.Response

interface ApiHelper {

    suspend fun getLecturers(): Response<List<Lecturer>>
    suspend fun getLanguages(): Response<List<Language>>

//    suspend fun postAnswers(languages: List<Language>)
}