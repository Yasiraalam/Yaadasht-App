package com.example.notemaking.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes_table")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="NoteTitle")
    val noteTitle :String,
    @ColumnInfo(name="NoteBody")
    val noteBody :String
) : Parcelable
