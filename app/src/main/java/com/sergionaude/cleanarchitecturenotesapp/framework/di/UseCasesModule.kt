package com.sergionaude.cleanarchitecturenotesapp.framework.di

import com.sergionaude.cleanarchitecturenotesapp.framework.UseCases
import com.sergionaude.core.repository.NoteRepository
import com.sergionaude.core.usecase.AddNote
import com.sergionaude.core.usecase.GetAllNotes
import com.sergionaude.core.usecase.GetNote
import com.sergionaude.core.usecase.GetWordCount
import com.sergionaude.core.usecase.RemoveNote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class UseCasesModule {
    @Provides
    fun providesUseCases(repository: NoteRepository) =
        UseCases(
            AddNote(repository),
            GetAllNotes(repository),
            GetNote(repository),
            RemoveNote(repository),
            GetWordCount(),
        )
}
