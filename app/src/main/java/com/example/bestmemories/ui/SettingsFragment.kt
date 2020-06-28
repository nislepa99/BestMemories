package com.example.bestmemories.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bestmemories.R
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        btnCancelSettings.setOnClickListener {
            fragmentManager?.popBackStack()
            Log.i("ZACHTO", "NEPONYATNO")
        }

        btnChangeLanguage.setOnClickListener {
            val language = arrayOf("English", "Русский", "Français", "Deutsche")

             AlertDialog.Builder(context)
                 .setTitle("Choose your language")
                 .setItems(language
                 ) { dialog, it ->
                     Toast.makeText(activity, "Selected language: ${language[it]}",
                         Toast.LENGTH_SHORT).show()
                 }
                 .setPositiveButton("OK", null)
                 .setNegativeButton("Отмена", null)
                 .create()
                 .show()

        }

        btnChangeTheme.setOnClickListener {
            val themeStyle = arrayOf("Dark theme", "Light theme")

            AlertDialog.Builder(context)
                .setTitle("Choose theme")
                .setItems(themeStyle
                ) { dialog, it ->
                    Toast.makeText(activity, "Selected language: ${themeStyle[it]}",
                        Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancel", null)
                .create()
                .show()

        }
    }
}


//val selectedItems = ArrayList<Int>() // Where we track the selected items
//val builder = AlertDialog.Builder(it)
//builder.setTitle("Выберите любимое имя кота")
//.setSingleChoiceItems(catNames, -1
//) { dialog, item ->
//    Toast.makeText(activity, "Любимое имя кота:  ${catNames[item]}",
//        Toast.LENGTH_SHORT).show()
//}
//.setPositiveButton("OK"
//) { dialog, id ->
//    // User clicked OK, so save the selectedItems results somewhere
//    // or return them to the component that opened the dialog
//}
//.setNegativeButton("Отмена") {
//    dialog, id ->
//}
//
//builder.create()