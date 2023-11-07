package com.example.notemaking


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notemaking.data.Note
import com.example.notemaking.databinding.ActivityMainBinding
import com.example.notemaking.ui.INotesRVAdapter
import com.example.notemaking.ui.NotesRVAdapter
import com.example.notemaking.viewModel.NoteViewModel

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val allNotes= ArrayList<Note>()


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]

        viewModel.allNotes.observe(this) { list ->
            list?.let {
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                val adapter = NotesRVAdapter(this,allNotes ,this)
                binding.recyclerView.adapter = adapter
                adapter.updateList(it)
            }
        }
        binding.saveBtn.setOnClickListener {
            val note_text = binding.input.text.toString()
            if(note_text.isNotEmpty()){
                viewModel.insertNote(Note(0,note_text))
                Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
            }
        }
//        viewModel.allNotes.observe(this, Observer { list ->
//            list?.let {
//                adapter.updateList(it)
//            }
//
//        })
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_SHORT).show()
    }
}




