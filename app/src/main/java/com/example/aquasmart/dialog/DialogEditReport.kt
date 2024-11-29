package com.example.aquasmart.dialog


import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Binder
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.aquasmart.MainActivity
import com.example.aquasmart.R
import com.example.aquasmart.adapter.ViewHolderReports
import com.example.aquasmart.models.Reports
import com.example.aquasmart.models.ReportsArguments


class DialogEditReport(
    val reportToUpdate: Reports,
    val updateReportDialog: (Reports) -> Unit
) : DialogFragment() {

    lateinit var activity: MainActivity

    val ARGUMENT_NAME : String = ReportsArguments.ARGUMENT_NAME
    val ARGUMENT_CLIENTNAME : String = ReportsArguments.ARGUMENT_CLIENTENAME
    val ARGUMENT_DATE : String = ReportsArguments.ARGUMENT_DATE
    val ARGUMENT_DESCRIPTION = ReportsArguments.ARGUMENT_DESCRIPTION
    val ARGUMENT_IMAGE = ReportsArguments.ARGUMENT_IMAGE

    init {
        // Se prepara el Bundle para pasarle al dialogo
        val args = Bundle().apply {
            putString(ARGUMENT_NAME, reportToUpdate.name)
            putString(ARGUMENT_CLIENTNAME, reportToUpdate.clientName)
            putString(ARGUMENT_DATE, reportToUpdate.date.toString())
            putString(ARGUMENT_DESCRIPTION, reportToUpdate.description)
            putString(ARGUMENT_IMAGE, reportToUpdate.image)
        }
        this.arguments = args
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflater = it.layoutInflater

            val viewDialogEditReport = inflater.inflate(R.layout.fragment_edit_report, null)
            setValuesIntoDialog(viewDialogEditReport, this.arguments)

            builder.setView(viewDialogEditReport)
                .create()


        } ?: throw IllegalStateException("Activity cannot be null")) as Dialog
    }

    private fun setValuesIntoDialog(viewDialogEditReport: View, arguments: Bundle?) {

        // Pagina 90 presentación

    }
}