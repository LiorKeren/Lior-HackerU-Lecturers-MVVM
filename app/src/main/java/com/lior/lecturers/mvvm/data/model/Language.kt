package com.lior.lecturers.mvvm.data.model

import com.squareup.moshi.Json

data class Language(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String = " "
){
    override fun toString(): String {
        return name
    }
}
