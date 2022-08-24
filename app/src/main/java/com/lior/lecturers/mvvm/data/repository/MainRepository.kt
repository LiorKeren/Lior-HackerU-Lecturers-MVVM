package com.lior.lecturers.mvvm.data.repository

import com.lior.lecturers.mvvm.data.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getLecturers() =  apiHelper.getLecturers()
    suspend fun getLanguages() =  apiHelper.getLanguages()

}