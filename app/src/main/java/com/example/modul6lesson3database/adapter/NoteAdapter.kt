package com.example.modul6lesson3database.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.modul6lesson3database.databinding.ItemNoteBinding
import com.example.modul6lesson3database.model.Note

class NoteAdapter(var list: List<Note>) : RecyclerView.Adapter<NoteAdapter.VH>() {

    inner class VH(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(note: Note){
            binding.tvTime.text = note.time
            binding.tvNote.text = note.note
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}