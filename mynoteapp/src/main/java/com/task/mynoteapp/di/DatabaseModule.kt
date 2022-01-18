package com.task.mynoteapp.di

import android.content.Context
import androidx.room.Room
import com.task.data.local.NoteAppDb
import com.task.data.local.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext appContext: Context): NoteAppDb {
        return Room.databaseBuilder(
            appContext,
            NoteAppDb::class.java,
            "NoteApp"
        ).build()
    }

    @Provides
    fun provideNoteDao(appDatabase: NoteAppDb): NoteDao {
        return appDatabase.noteDao()
    }
}