package com.example.youtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity(), OnItemClickListener {

    lateinit var binding : ActivityMain2Binding
    lateinit var viewModel:NoteViewModel
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)



        
        binding.button.setOnClickListener{
            val noteText = binding.editTextTextPersonName.text.toString()
            if(noteText.isNotEmpty()){
                viewModel.insertNote(Note(noteText))
                Toast.makeText(this,"Item Inserted",Toast.LENGTH_SHORT).show()
            }

        }

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {
            val list = mutableListOf<Note>()
            list.addAll(it)
            adapter = NoteAdapter(list,this)
            binding.rvNotes.adapter = adapter
            binding.rvNotes.layoutManager = LinearLayoutManager(this)
        })
    }

    override fun onItemClicked(position: Int) {
        viewModel.deleteNode(adapter.notes[position])
        Toast.makeText(this,"Item Deleted",Toast.LENGTH_SHORT).show()
    }
}