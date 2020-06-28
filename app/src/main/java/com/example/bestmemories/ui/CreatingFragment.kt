package com.example.bestmemories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bestmemories.R
import com.example.bestmemories.data.NoteRepository
import kotlinx.android.synthetic.main.fragment_note_creating.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class CreatingFragment : Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_creating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repo = NoteRepository()

        btnAddFile.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.add(
                    R.id.fragmentContainer,
                    AttachFragment()
                )
                ?.addToBackStack(null)
                ?.commit()
        }

        btnNewNote.setOnClickListener {

            val date = noteDate.text.toString()
            val title = noteTitle.text.toString()
            val text = noteText.text.toString()

            if (date.isNotBlank() && title.isNotBlank() && text.isNotBlank()) {
                launch {
                    val id = repo.createNewNote(date, title, text)
                    Toast.makeText(context, "Memories number $id was created", Toast.LENGTH_SHORT).show()

                    fragmentManager?.beginTransaction()
                        ?.replace(
                            R.id.fragmentContainer,
                            ListFragment()
                        )
                        ?.addToBackStack(null)
                        ?.commit()
                }
            }

            else {
                Toast.makeText(context, "Please fill in all fields to create a memory", Toast.LENGTH_SHORT).show()
            }
        }
    }
}