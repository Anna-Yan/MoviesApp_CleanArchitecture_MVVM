package com.annazaqaryan.moviesbyaz.util


import android.content.Context
import android.widget.Toast


fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
