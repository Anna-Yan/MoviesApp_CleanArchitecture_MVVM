package com.annazaqaryan.moviesbyaz.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.annazaqaryan.moviesbyaz.R
import com.annazaqaryan.moviesbyaz.MovieService.Companion.startService
import com.annazaqaryan.moviesbyaz.MovieService.Companion.stopService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService()
    }
}