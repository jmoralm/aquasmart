package com.example.aquasmart.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.aquasmart.R
import com.example.aquasmart.databinding.EditReportBinding
import com.example.aquasmart.models.Reports
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogDeleteReport(
    val reportToDelete: Reports,
    val deleteReportDialog: (Reports) -> (Unit)

) : DialogFragment() {

    private lateinit var imageReport: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //
        val inflater = requireActivity().layoutInflater
        val viewDialogEditReport = inflater.inflate(R.layout.edit_report, null)
        val binding = EditReportBinding.bind(viewDialogEditReport)

        // Configurar datos iniciales
        setValuesIntoDialog(binding)

        return AlertDialog.Builder(requireActivity()).setView(viewDialogEditReport)
            .setTitle("Borrar Informe")
            .setMessage("¿Desea borrar este reporte?")
            .setIcon(R.drawable.baseline_delete_outline_24)
            .setPositiveButton("Aceptar") { _, _ ->

                deleteReportDialog(reportToDelete)
                dismiss() // Cierra el diálogo al aceptar

            }.setNegativeButton("Cancelar") { _, _ ->
                dismiss()
            }
            .create()
    }

    private fun setValuesIntoDialog(binding: EditReportBinding) {
        binding.etReportName.setText(reportToDelete.name)
        binding.etReportName.isEnabled = false
        binding.etClientName.setText(reportToDelete.clientName)
        binding.etClientName.isEnabled = false
        binding.editTextDate.setText(reportToDelete.date)
        binding.editTextDate.isEnabled = false
        binding.editTextMultiDescription.setText(reportToDelete.description)
        binding.editTextMultiDescription.isEnabled = false
        imageReport = reportToDelete.image
    }

}