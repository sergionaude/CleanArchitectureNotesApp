package com.sergionaude.cleanarchitecturenotesapp.framework.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class ApplicationModule(
    val app: Application,
) {
    @Provides
    fun getApplication() = app
}
