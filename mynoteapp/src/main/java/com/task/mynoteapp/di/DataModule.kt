package com.task.mynoteapp.di

import com.task.data.local.NoteDatabase
import com.task.data.local.NoteLocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideLocalRepository(repository: NoteLocalRepository): NoteDatabase
}