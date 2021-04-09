package com.example.youtube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.NoteItemBinding

class NoteAdapter(
    var notes:List<Note>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: NoteItemBinding):RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        init {
            binding.imageView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position!= RecyclerView.NO_POSITION){
                listener.onItemClicked(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return  NoteViewHolder(NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding.apply {
            textView.text = notes[position].text
        }
    }
}

interface OnItemClickListener{
    fun onItemClicked(position: Int)
}