package com.sergionaude.cleanarchitecturenotesapp.framework.di

import android.app.Application
import com.sergionaude.cleanarchitecturenotesapp.framework.RoomDataBaseService
import com.sergionaude.core.repository.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class RepositoryNoteModule {
    @Provides
    fun providesRepository(app: Application) = NoteRepository(RoomDataBaseService(context = app))
}
