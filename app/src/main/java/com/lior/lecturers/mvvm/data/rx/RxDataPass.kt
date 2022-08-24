package com.lior.lecturers.mvvm.data.rx

import com.lior.lecturers.mvvm.data.model.Language
import io.reactivex.rxjava3.subjects.BehaviorSubject

class RxDataPass {

    private val behaviorSubject: BehaviorSubject<Language> = BehaviorSubject.create()

    //Subscribe an Answer when item click
    fun getListItemClickSubject(): BehaviorSubject<Language> {
        return behaviorSubject
    }
}