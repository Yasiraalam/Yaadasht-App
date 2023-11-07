package com.example.notemaking.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notemaking.data.Note
import com.example.notemaking.data.NoteDatabase
import com.example.notemaking.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {
    val allNotes:LiveData<List<Note>>
    private val repository:NoteRepository
    init {
        val dao = NoteDatabase.getInstance(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
         repository.delete(note)
    }
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}