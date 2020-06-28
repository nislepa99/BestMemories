package com.example.bestmemories.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.bestmemories.R
import kotlinx.android.synthetic.main.fragment_camera.*
import java.io.File
import java.io.IOException
import java.util.*


class CameraFragment : Fragment() {

    private val REQUEST_PHOTO = 42
    private var photoUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        takePhoto.setOnClickListener { dispatchTakePhotoIntent() }

        addPhoto.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fragmentContainer,
                    CreatingFragment()
                )
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    private fun dispatchTakePhotoIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).let { takePhotoIntent ->
            activity?.packageManager?.let { bit ->
                takePhotoIntent.resolveActivity(bit)?.let {
                    val photoFile = try {
                        createImageFile()
                    } catch (e: IOException) {
                        null
                    }

                    photoFile?.let {
                        photoUri = FileProvider.getUriForFile(
                            requireContext(),
                            "com.example.notesapp.fileprovider",
                            it
                        )
                        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                        startActivityForResult(takePhotoIntent, REQUEST_PHOTO)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PHOTO && resultCode == RESULT_OK) {
            photo.setImageURI(photoUri)
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val storageDir = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(UUID.randomUUID().toString(), ".jpg", storageDir)
    }
}