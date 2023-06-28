package com.aenatural.aenaturals.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.widget.TextView
import com.aenatural.aenaturals.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class DialogPB(private val activity: Activity) {
    private var dialog: AlertDialog? = null
    private var bottomDialog: BottomSheetDialog? = null

    @SuppressLint("InflateParams")
    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.waiting, null))
        builder.setCancelable(false)

        dialog = builder.create()
        dialog?.show()
    }
    fun showErrorBottomSheetDialog(errorMessage: String) {
        val bottomSheetDialog = BottomSheetDialog(activity)
        val view = activity.layoutInflater.inflate(R.layout.error_dialog, null)

        val errorTextView = view.findViewById<TextView>(R.id.error_loginerrorTV)
        errorTextView.text = errorMessage

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
    fun dismissDialog() {

        dialog?.dismiss()

    }
}