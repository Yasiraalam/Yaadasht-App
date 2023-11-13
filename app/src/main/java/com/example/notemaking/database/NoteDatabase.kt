package com.example.notemaking.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notemaking.model.Note

@Database(entities = [Note::class], version =1, exportSchema = false)
abstract class NoteDatabase :RoomDatabase(){

    abstract fun getNoteDao():NoteDao

    companion object{
        private var INSTANCE :NoteDatabase?=null
        fun getInstance(context: Context):NoteDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "Note_database"
                ).build()
                INSTANCE = instance
                instance

            }
        }
    }
}