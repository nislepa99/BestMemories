package com.example.bestmemories.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bestmemories.R
import com.example.bestmemories.ui.NavFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragmentContainer,
                NavFragment()
            )
            .commit()
    }

}

