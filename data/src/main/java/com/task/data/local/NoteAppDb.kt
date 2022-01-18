package com.task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.data.local.dao.NoteDao
import com.task.data.local.entity.Note

@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class NoteAppDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}