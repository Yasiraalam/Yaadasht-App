package com.example.notemaking.fragments

import android.app.AlertDialog
import android.app.Application
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
import androidx.navigation.fragment.navArgs
import com.example.notemaking.R
import com.example.notemaking.databinding.FragmentUpdateNoteBinding
import com.example.notemaking.model.Note
import com.example.notemaking.ui.adapters.NotesRVAdapter
import com.example.notemaking.viewModel.NoteViewModel

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {
    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!
    private val args: UpdateNoteFragmentArgs by navArgs()
    private lateinit var currentNote: Note
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireContext().applicationContext as Application)
        )[NoteViewModel::class.java]
        currentNote = args.note!!
        binding.NoteTitleUpdate.setText(currentNote.noteTitle)
        binding.NoteBodyUpdate.setText(currentNote.noteBody)
        binding.fabUpdate.setOnClickListener {
            val title = binding.NoteTitleUpdate.text.toString().trim()
            val body = binding.NoteBodyUpdate.text.toString().trim()

            if (title.isNotEmpty()) {
                val note = Note(currentNote.id, title, body)
                noteViewModel.updateNote(note)
                view.findNavController().navigate(
                    R.id.action_updateNoteFragment_to_homeFragment
                )

            } else {
                Toast.makeText(requireContext(), "Please Enter title name!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }


//    private fun deleteNote() {
//        AlertDialog.Builder(requireActivity()).apply {
//            setTitle("Delete Note")
//            setMessage("Are you sure want to delete this Note?")
//            setPositiveButton("DELETE") { _, _ ->
//                noteViewModel.deleteNote(currentNote)
//                view?.findNavController()?.navigate(
//                    R.id.action_updateNoteFragment_to_homeFragment
//                )
//            }
//            setNegativeButton("CANCEL", null)
//        }.create().show()
//    }

//    @Deprecated("Deprecated in Java")
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.delete_menu -> {
//                deleteNote()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//
//    }

//    @Deprecated("Deprecated in Java")
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.update_menu, menu)
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}