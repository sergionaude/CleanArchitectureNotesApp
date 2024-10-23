package com.sergionaude.cleanarchitecturenotesapp.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergionaude.cleanarchitecturenotesapp.R
import com.sergionaude.core.data.Note

class NoteListAdapter(
    private var noteList: MutableList<Note>,
) : RecyclerView.Adapter<NoteViewHolder>() {
    fun updateNotes(newNotes: List<Note>)  {
        noteList.clear()
        noteList.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NoteViewHolder =
        NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false),
        )

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int,
    ) {
        holder.bind(note = noteList[position])
    }
}
