package com.aenatural.aenaturals.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.aenatural.aenaturals.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class DialogPB(private val activity: Activity) {
    private var dialog: AlertDialog? = null
    private var sucessDialog: AlertDialog? = null
    private var bottomDialog: BottomSheetDialog? = null
    lateinit var sucess_dialog_back: Button

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
    fun startSucessDialog(msg:String, context: Context, activityName:Class<*>){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_success, null))
        builder.setCancelable(true)
        sucessDialog = builder.create()
        sucessDialog?.show()

        val successMsg = sucessDialog?.findViewById<TextView>(R.id.successMsg)
        successMsg?.text = msg

        sucessDialog?.findViewById<Button>(R.id.sucess_dialog_back)?.setOnClickListener {
var intent = Intent(context, activityName)
    context.startActivity(intent)
            sucessDialog?.dismiss()
        }
    }
    fun dismissSucessDialog() {
        sucessDialog?.dismiss()
    }

}