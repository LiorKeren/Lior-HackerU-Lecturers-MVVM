package com.lior.lecturers.mvvm.ui.main.view

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lior.lecturers.mvvm.R
import com.lior.lecturers.mvvm.data.model.Language
import com.lior.lecturers.mvvm.data.model.Lecturer
import com.lior.lecturers.mvvm.data.rx.RxDataPass
import com.lior.lecturers.mvvm.ui.main.adapter.MainAdapter
import com.lior.lecturers.mvvm.ui.main.viewmodel.MainViewModel
import com.lior.lecturers.mvvm.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val rxDataPass: RxDataPass by inject()
    private val mainViewModel : MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()

    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.adapter = adapter
//        submitButton.setOnClickListener { mainViewModel.postAnswers() }
    }

    private fun setupObserver() {
        mainViewModel.lecturers.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { lecturers -> renderList(lecturers) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> { onLoading()
                }
                Status.ERROR -> { onError(it.message) }
            }
        })
        mainViewModel.lecturersFilteredList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { questions -> renderList(questions) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    onLoading()
                }
                Status.ERROR -> {
                    onError(it.message)

                }
            }
        })
        mainViewModel.languages.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { languages ->
                        val aa = ArrayAdapter(this, R.layout.spinner_item,
                            languages)
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                        with(spinner)
                        {
                            adapter = aa
                            setSelection(0, false)
                            onItemSelectedListener = this@MainActivity
                            prompt = context.getString(R.string.choose_language)
                            gravity = Gravity.CENTER
                            setPopupBackgroundResource(R.color.material_grey_600)


                        }
                    }
                }
                Status.LOADING -> {
                    onLoading()
                }
                Status.ERROR -> {
                    onError(it.message)
                }
            }
        })

        mainViewModel.isAllAnswersFilled.observe(this, Observer {
            val toastMessageStr  = if (it){
                "Thank you for your time !"
            }else{
                "Please fill all required questions !"
            }
            Toast.makeText(this, toastMessageStr, Toast.LENGTH_LONG).show()
        })

    }

    private fun onLoading() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun onError(message: String?) {
        //Handle Error
        progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun renderList(lecturers: List<Lecturer>) {
        adapter.addData(lecturers)
        adapter.notifyDataSetChanged()
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        showToast(message = "Nothing selected")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        rxDataPass.getListItemClickSubject().onNext(parent?.getItemAtPosition(position) as Language)
    }

    private fun showToast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(context, message, duration).show()
    }

}
