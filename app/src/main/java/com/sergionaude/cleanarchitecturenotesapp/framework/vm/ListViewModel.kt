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

class ListViewModel(
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

    val notesList = MutableLiveData<List<Note>>()

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val noteList = useCases.getAllNotes()
            notesList.postValue(noteList)
        }
    }
}
