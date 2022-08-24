package com.lior.lecturers.mvvm.data.api

import com.lior.lecturers.mvvm.data.model.Language
import com.lior.lecturers.mvvm.data.model.Lecturer
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getLecturers(): Response<List<Lecturer>> = apiService.getLecturers()
    override suspend fun getLanguages(): Response<List<Language>> = apiService.getLanguages()

//    override suspend fun postAnswers(languages: List<Language>) = apiService.postAnswers(languages)

}