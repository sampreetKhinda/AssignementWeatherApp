package com.sam.assignementweatherapp.utils

import android.app.AlertDialog
import android.content.Context
import com.sam.assignementweatherapp.R

fun showAlertDialog(context: Context, alertTitle: String, alertMsg: String) {
    AlertDialog.Builder(context)
        .setTitle(alertTitle)
        .setMessage(alertMsg)
        .setPositiveButton(
            R.string.ok_text
        ) { dialog, which ->
            // Continue with delete operation
        }
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show()
}