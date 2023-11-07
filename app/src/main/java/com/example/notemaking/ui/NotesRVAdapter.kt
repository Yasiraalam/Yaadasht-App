package com.example.notemaking.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notemaking.MainActivity
import com.example.notemaking.data.Note
import com.example.notemaking.databinding.ItemNoteBinding


class NotesRVAdapter(
    private val context: Context,
    private val allNotes: ArrayList<Note>,
    val listener: INotesRVAdapter
) : RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =ItemNoteBinding.inflate(LayoutInflater.from(context),parent,false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = allNotes[position]
        holder.bind(item)
    }

    inner class NoteViewHolder(private val binding:ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Note) {
            binding.noteText.text = item.text
            binding.deleteBtn.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }
    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}