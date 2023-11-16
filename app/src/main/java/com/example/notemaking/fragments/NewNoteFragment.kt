package com.example.notemaking.fragments

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.notemaking.R
import com.example.notemaking.databinding.FragmentNewNoteBinding
import com.example.notemaking.model.Note
import com.example.notemaking.ui.MainActivity
import com.example.notemaking.viewModel.NoteViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.Random


class NewNoteFragment : Fragment(R.layout.fragment_new_note) {
    private var _binding:FragmentNewNoteBinding?=null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var mView:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application = Application())
        )[NoteViewModel::class.java]
        mView = view
    }
    private fun saveNote(view:View){
        val noteTitle = binding.NoteTitle.text.toString().trim()
        val noteBody = binding.NoteBody.text.toString().trim()
        if(noteTitle.isNotEmpty()){
            val note = Note(0,noteTitle,noteBody)
            noteViewModel.insertNote(note)
            Snackbar.make(view,"Note saved successfully",Snackbar.LENGTH_SHORT).show()

            view.findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)
        }else{
            Toast.makeText(requireContext(), "Title Shouldn't Empty", Toast.LENGTH_SHORT).show()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.save_menu ->{
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)

    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.new_note_menu,menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}