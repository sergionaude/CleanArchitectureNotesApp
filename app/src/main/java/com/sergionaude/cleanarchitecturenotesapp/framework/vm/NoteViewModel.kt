package com.sergionaude.cleanarchitecturenotesapp.framework.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sergionaude.cleanarchitecturenotesapp.framework.UseCases
import com.sergionaude.cleanarchitecturenotesapp.framework.di.ApplicationModule
import com.sergionaude.cleanarchitecturenotesapp.framework.di.DaggerViewModelComponent
import com.sergionaude.core.data.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel(
    application: Application,
) : AndroidViewModel(application = application) {
    @Inject lateinit var useCases: UseCases

    val saved = MutableLiveData(false)

    init {
        DaggerViewModelComponent
            .builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)

        saved.postValue(false)
    }

    val note = MutableLiveData<Note?>()

    fun saveNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.addNote(note = note)
            saved.postValue(true)
        }
    }

    fun getNote(idNote: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            note.postValue(useCases.getNote(id = idNote))
        }
    }

    fun removeNote(noteValue: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.removeNote(note = noteValue)
        }
    }
}
