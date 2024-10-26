package com.sergionaude.cleanarchitecturenotesapp.presentation.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sergionaude.cleanarchitecturenotesapp.R
import com.sergionaude.core.data.Note
import java.text.SimpleDateFormat
import java.util.Date

class NoteViewHolder(
    view: View,
    private val listAction: ListAction,
) : RecyclerView.ViewHolder(view) {
    private val noteLayout = view.findViewById<View>(R.id.item_note_layout)
    private val noteTitle = view.findViewById<TextView>(R.id.item_note_title)
    private val noteDescription = view.findViewById<TextView>(R.id.item_note_content)
    private val noteDate = view.findViewById<TextView>(R.id.item_note_date)
    private val noteWordsNumber = view.findViewById<TextView>(R.id.item_note_count_words)

    fun bind(note: Note) {
        noteTitle.text = note.title
        noteDescription.text = note.content
        val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
        val resultDate = Date(note.updatedTime)
        noteDate.text = "Last updated: ${sdf.format(resultDate)}"

        noteLayout.setOnClickListener {
            listAction.onClick(note.id)
        }

        noteWordsNumber.text = "Words: ${note.wordCount}"
    }
}
