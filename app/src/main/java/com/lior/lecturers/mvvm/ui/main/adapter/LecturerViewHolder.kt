package com.lior.lecturers.mvvm.ui.main.adapter

import android.text.Editable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lior.lecturers.mvvm.data.model.Lecturer
import kotlinx.android.synthetic.main.item_layout.view.*
import org.koin.core.KoinComponent

class LecturerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), KoinComponent {
    private lateinit var questionText: String

    fun bind(lecturer: Lecturer) {
        questionText = lecturer.id
        //For re loading the list because we are using recyclerView the prev data is still there
        itemView.radioGroup.removeAllViews()
        itemView.radioGroup.clearCheck( )

        itemView.answerTypeTextEditText.visibility = View.GONE
        itemView.radioGroup.visibility = View.GONE

        itemView.answerTypeTextEditText.text = Editable.Factory.getInstance().newEditable("")

        itemView.textViewName.text = lecturer.name
        itemView.textViewEmail.text = lecturer.email
    }

}