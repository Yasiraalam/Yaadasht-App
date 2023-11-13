package com.example.notemaking.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notemaking.model.Note
import com.example.notemaking.databinding.ItemNoteBinding
import com.example.notemaking.fragments.HomeFragmentDirections


class NotesRVAdapter(
    private val onLongPressListener: (Note) -> Unit
) : RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
    private var binding:ItemNoteBinding?=null

private  val differCallback=
    object :DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem==newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        binding =ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding!!)
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemView.apply {
            binding?.tvNoteTitle?.text =currentNote.noteTitle
            binding?.tvNoteBody?.text =currentNote.noteBody
        }.setOnClickListener { mView->
            val direction  = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currentNote)
            mView.findNavController().navigate(direction)
        }
        holder.itemView.setOnLongClickListener {
            // Handle long press
            onLongPressListener.invoke(currentNote)
            true
        }
    }

    inner class NoteViewHolder(itemBinding:ItemNoteBinding) : RecyclerView.ViewHolder(itemBinding.root)
//    {
//        fun bind(item: Note) {
//            binding.noteText.text = item.text
//            binding.deleteBtn.setOnClickListener {
//                listener.onItemClicked(item)
//            }
//        }
//    }
//    fun updateList(newList: List<Note>){
//        allNotes.clear()
//        allNotes.addAll(newList)
//        notifyDataSetChanged()
//    }
}
//interface INotesRVAdapter{
//    fun onItemClicked(note: Note)
//}