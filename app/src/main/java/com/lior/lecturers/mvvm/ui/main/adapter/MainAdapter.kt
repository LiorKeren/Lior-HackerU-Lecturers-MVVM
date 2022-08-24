package com.lior.lecturers.mvvm.ui.main.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lior.lecturers.mvvm.R
import com.lior.lecturers.mvvm.data.model.Lecturer
class MainAdapter(
    private val lecturers: ArrayList<Lecturer>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturerViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout, parent,
            false
        )
        return LecturerViewHolder(view)
    }

    override fun getItemCount(): Int = lecturers.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        /*
        If we will want to create another item ViewHolder we can do this here
         */
        (holder as LecturerViewHolder).bind(lecturers[position])
    }

    fun addData(list: List<Lecturer>) {
        lecturers.clear()
        lecturers.addAll(list)
    }

}