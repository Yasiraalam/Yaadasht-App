package com.example.notemaking.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notemaking.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)
    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notes_table order by id DESC")
     fun getAllNotes():LiveData<List<Note>>
}