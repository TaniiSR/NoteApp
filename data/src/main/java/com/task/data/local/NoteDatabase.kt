package com.task.data.local

import com.task.data.local.entity.Note

interface NoteDatabase {
    suspend fun getAllNotes(): List<Note>
    suspend fun insertNotes(notes: List<Note>)
    suspend fun insertNote(note: Note): Long
    suspend fun updateNote(note: Note): Int
    suspend fun deleteNote(note: Note): Int

}