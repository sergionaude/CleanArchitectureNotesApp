package com.sergionaude.cleanarchitecturenotesapp.framework.di

import com.sergionaude.cleanarchitecturenotesapp.framework.vm.ListViewModel
import com.sergionaude.cleanarchitecturenotesapp.framework.vm.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryNoteModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)

    fun inject(lisNoteViewModel: ListViewModel)
}
