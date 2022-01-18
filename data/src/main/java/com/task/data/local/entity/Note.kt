package com.task.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val noteId: Long = 0,
    @ColumnInfo(name = "noteTitle") var noteTitle: String,
    @ColumnInfo(name = "noteDescription") var noteDescription: String,
    @ColumnInfo(name = "noteImageUrl") var noteImageUrl: String,
    @ColumnInfo(name = "noteData") var noteDate: String,
    @ColumnInfo(name = "isNoteUpdated") var isNoteUpdated: Boolean
) : Parcelable

