package com.aenatural.aenaturals.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import com.aenatural.aenaturals.R

class DialogPB(private val activity: Activity) {
    private var dialog: AlertDialog? = null

    @SuppressLint("InflateParams")
    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.waiting, null))
        builder.setCancelable(false)

        dialog = builder.create()
        dialog?.show()
    }

    fun dismissDialog() {

        dialog?.dismiss()

    }
}