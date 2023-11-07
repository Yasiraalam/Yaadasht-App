package com.example.notemaking.data

import android.content.Context
import android.os.Build.VERSION
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

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