package com.task.data.local.dao

import androidx.room.*
import com.task.data.local.entity.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note): Int

    @Delete
    suspend fun delete(note: Note): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: List<Note>)

}