package com.example.bestmemories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bestmemories.R
import kotlinx.android.synthetic.main.fragment_navigation.*


class NavFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        notesListBtn.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fragmentContainer,
                    ListFragment()
                )
                ?.addToBackStack(null)
                ?.commit()
        }

        btnSettings.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.add(
                    R.id.fragmentContainer,
                    SettingsFragment()
                )
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}