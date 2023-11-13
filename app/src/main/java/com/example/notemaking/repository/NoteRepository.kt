package com.example.notemaking.repository
import androidx.lifecycle.LiveData
import com.example.notemaking.database.NoteDao
import com.example.notemaking.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes:LiveData<List<Note>> = noteDao.getAllNotes()
    suspend fun insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
}