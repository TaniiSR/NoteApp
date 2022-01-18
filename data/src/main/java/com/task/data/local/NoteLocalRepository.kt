package com.task.data.local

import com.task.data.local.dao.NoteDao
import com.task.data.local.entity.Note
import javax.inject.Inject

class NoteLocalRepository @Inject constructor(private val noteDao: NoteDao) : NoteDatabase {
    override suspend fun getAllNotes(): List<Note> = noteDao.getAllNotes()

    override suspend fun insertNotes(notes: List<Note>) = noteDao.insertNotes(notes)

    override suspend fun insertNote(note: Note): Long {
        return noteDao.insertNote(note)
    }

    override suspend fun updateNote(note: Note): Int {
        return noteDao.update(note)

    }

    override suspend fun deleteNote(note: Note): Int {
        return noteDao.delete(note)
    }
}