package com.example.notemaking.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notemaking.R
import com.example.notemaking.model.Note
import com.example.notemaking.databinding.ItemNoteBinding
import com.example.notemaking.fragments.HomeFragmentDirections
import java.util.Random


class NotesRVAdapter(
    private val context: Context,
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
            binding?.noteCard?.setBackgroundColor(getRandomColor(context))
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

    private fun getRandomColor(context: Context): Int {
        val random = Random()
        val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        return color

        /* choose some color and generate random color for note then use this code snippet*/
//        val Colors = arrayOf(
//           R.color.NoteColor1,
//           R.color.NoteColor2,
//           R.color.NoteColor3,
//           R.color.NoteColor4,
//           R.color.NoteColor5,
//           R.color.NoteColor6
//        )
//        val randomIndex = (Colors.indices).random()
//        return ContextCompat.getColor(context, Colors[randomIndex])

    }

    inner class NoteViewHolder(itemBinding:ItemNoteBinding) : RecyclerView.ViewHolder(itemBinding.root)

}
