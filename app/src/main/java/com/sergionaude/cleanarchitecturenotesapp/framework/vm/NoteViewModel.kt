package com.sergionaude.cleanarchitecturenotesapp.framework.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sergionaude.cleanarchitecturenotesapp.framework.RoomDataBaseService
import com.sergionaude.cleanarchitecturenotesapp.framework.UseCases
import com.sergionaude.core.data.Note
import com.sergionaude.core.repository.NoteRepository
import com.sergionaude.core.usecase.AddNote
import com.sergionaude.core.usecase.GetAllNotes
import com.sergionaude.core.usecase.GetNote
import com.sergionaude.core.usecase.RemoveNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(
    application: Application,
) : AndroidViewModel(application = application) {
    private val noteRepository = NoteRepository(RoomDataBaseService(context = application))

    private val useCases =
        UseCases(
            addNote = AddNote(noteRepository = noteRepository),
            getNote = GetNote(noteRepository = noteRepository),
            getAllNotes = GetAllNotes(noteRepository = noteRepository),
            removeNote = RemoveNote(noteRepository = noteRepository),
        )

    val saved = MutableLiveData<Boolean>()

    init {
        saved.postValue(false)
    }

    fun saveNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.addNote(note = note)
            saved.postValue(true)
        }
    }
}
