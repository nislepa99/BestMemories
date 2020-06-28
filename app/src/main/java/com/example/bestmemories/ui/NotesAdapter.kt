package com.example.bestmemories.ui

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmemories.R
import com.example.bestmemories.data.Note
import kotlinx.android.synthetic.main.notes_list_item.view.*


class NotesAdapter(
    private val deleteNote: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    private val notes: MutableList<Note> = mutableListOf()

    fun addNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_list_item, parent, false)
        return NotesHolder(view)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.bind(notes[position]) {
            deleteNote(it) // удаляем из БД
            notes.removeAt(position) // удаляем из списка ресайклера
            notifyItemRemoved(position) // инициируем перерисовку
            notifyItemRangeChanged(position, itemCount)
        }
    }

    class NotesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note, deleteNote: (Note) -> Unit) = itemView.apply {
            noteDateItem.text = note.date
            noteTitleItem.text = note.title
            noteTextItem.text = note.text

            buttonDeleteNote.setOnClickListener {
                AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setTitle("A u sure?")
                    .setMessage("You will not be able to restore it")
                    .setPositiveButton(R.string.agree) { dialog, id ->
                        deleteNote(note)
                    }
                    .setNegativeButton(R.string.cancel, null)
                    .show()
            }
        }
    }
}