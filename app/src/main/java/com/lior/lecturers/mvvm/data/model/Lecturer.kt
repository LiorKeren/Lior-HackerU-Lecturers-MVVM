package com.lior.lecturers.mvvm.data.model

import com.squareup.moshi.Json

data class Lecturer(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "languages")
    val languages: List<Int> = arrayListOf()
)