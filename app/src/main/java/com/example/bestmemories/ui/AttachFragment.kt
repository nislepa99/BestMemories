package com.example.bestmemories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bestmemories.R
import kotlinx.android.synthetic.main.fragment_attach.*


class AttachFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attach, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        btnAddPhoto.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, CameraFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        btnCancelAttachment.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }
}