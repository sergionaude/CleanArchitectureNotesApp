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

class ListViewModel(
    application: Application,
) : AndroidViewModel(application = application) {
    @Inject lateinit var useCases: UseCases

    val notesList = MutableLiveData<List<Note>>()

    init {
        DaggerViewModelComponent
            .builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val noteList = useCases.getAllNotes()
            noteList.forEach {
                it.wordCount = useCases.getWordCount.invoke(it)
            }
            notesList.postValue(noteList)
        }
    }
}
