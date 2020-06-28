package com.example.bestmemories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bestmemories.R
import com.example.bestmemories.data.NoteRepository
import com.example.bestmemories.ui.CreatingFragment
import kotlinx.android.synthetic.main.fragment_notes_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListFragment() : Fragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val repo = NoteRepository()

        val adapter = NotesAdapter {
            launch {
                repo.deleteNote(it)
            }
        }

        notesList.adapter = adapter
        launch {
            adapter.addNotes(repo.getAllNotes())
        }

        btnCreateNew.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fragmentContainer,
                    CreatingFragment()
                )
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}