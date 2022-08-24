package com.lior.lecturers.mvvm.ui.main.viewmodel

import androidx.lifecycle.*
import com.lior.lecturers.mvvm.data.model.Language
import com.lior.lecturers.mvvm.data.model.Lecturer
import com.lior.lecturers.mvvm.data.repository.MainRepository
import com.lior.lecturers.mvvm.data.rx.RxDataPass
import com.lior.lecturers.mvvm.utils.NetworkHelper
import com.lior.lecturers.mvvm.utils.Resource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper,
    private val rxDataPass: RxDataPass
) : ViewModel() {

    private var _compositeDisposable = CompositeDisposable()
    private val compositeDisposable: CompositeDisposable
    get() = _compositeDisposable

    private val _isAllAnswersFilled = MutableLiveData<Boolean>()
    val isAllAnswersFilled: LiveData<Boolean>
        get() = _isAllAnswersFilled


    private val _lecturers = MutableLiveData<Resource<List<Lecturer>>>()
    val lecturers: LiveData<Resource<List<Lecturer>>>
        get() = _lecturers
    var lecturersFilteredList = MutableLiveData<Resource<List<Lecturer>>>()


    private val _languages = MutableLiveData<Resource<List<Language>>>()
    val languages: LiveData<Resource<List<Language>>>
        get() = _languages


    init {
        fetchLanguages()
        fetchLecturers()
        onSpinnerItemClick()
    }

    private fun onSpinnerItemClick(){
        compositeDisposable.add(
        rxDataPass.getListItemClickSubject().subscribe { language ->

            lecturersFilteredList.value = _lecturers.value

            lecturersFilteredList.postValue(Resource.success(lecturersFilteredList.value?.data!!.filter { lecturer ->

                lecturer.languages.contains(language.id)

            }))
        })
    }

    private fun fetchLecturers() {
        viewModelScope.launch {
            _lecturers.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getLecturers().let {
                    if (it.isSuccessful) {
                        _lecturers.postValue(Resource.success(it.body()))
                    } else _lecturers.postValue(Resource.error(it.errorBody().toString(), null))

                }
            } else _lecturers.postValue(Resource.error("No internet connection", null))
        }
    }

    private fun fetchLanguages() {
        viewModelScope.launch {
            _languages.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getLanguages().let {
                    if (it.isSuccessful) {
                        _languages.postValue(Resource.success(it.body()))
                    } else _languages.postValue(Resource.error(it.errorBody().toString(), null))

                }
            } else _languages.postValue(Resource.error("No internet connection", null))
        }
    }
}