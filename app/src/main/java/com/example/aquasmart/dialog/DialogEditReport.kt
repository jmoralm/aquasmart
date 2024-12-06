package com.example.aquasmart.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.aquasmart.R
import com.example.aquasmart.databinding.EditReportBinding
import com.example.aquasmart.models.Reports
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class DialogEditReport(
    val reportToUpdate: Reports,
    val updateReportDialog: (Reports) -> Unit
) : DialogFragment() {

    private lateinit var imageReport : String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //
        val inflater = requireActivity().layoutInflater
        val viewDialogEditReport = inflater.inflate(R.layout.edit_report, null)
        val binding = EditReportBinding.bind(viewDialogEditReport)

        // Configurar datos iniciales
        setValuesIntoDialog(binding)

        return MaterialAlertDialogBuilder(requireActivity())
            .setView(viewDialogEditReport)
            .setTitle("Editar Informe")
            .setMessage("Edite su informe y guarde los cambios")
            .setIcon(R.drawable.baseline_edit_document_24)
            .setPositiveButton("Aceptar") { _, _ ->
                val updatedReport = recoverDataLayout(binding)
                if (updatedReport.name.isEmpty() ||
                    updatedReport.clientName.isEmpty() ||
                    updatedReport.date.isEmpty() ||
                    updatedReport.description.isEmpty() ||
                    updatedReport.image.isEmpty()
                ) {
                    Toast.makeText(requireContext(), "Algún campo está vacío", Toast.LENGTH_LONG)
                        .show()
                } else {
                    updateReportDialog(updatedReport)
                    dismiss() // Cierra el diálogo al aceptar
                }
            }
            .setNegativeButton("Cancelar") { _, _ -> dismiss() }
            .create()
    }

    private fun setValuesIntoDialog(binding: EditReportBinding) {
        binding.etReportName.setText(reportToUpdate.name)
        binding.etClientName.setText(reportToUpdate.clientName)
        binding.editTextDate.setText(reportToUpdate.date)
        binding.editTextMultiDescription.setText(reportToUpdate.description)
        imageReport = reportToUpdate.image
    }

    private fun recoverDataLayout(binding: EditReportBinding): Reports {
        return Reports(
            name = binding.etReportName.text.toString().ifEmpty { "" },
            clientName = binding.etClientName.text.toString().ifEmpty { "" },
            date = binding.editTextDate.text.toString().ifEmpty { "" },
            description = binding.editTextMultiDescription.text.toString().ifEmpty { "" },
            image = imageReport
        )
    }
}