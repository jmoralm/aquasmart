package com.example.aquasmart.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.aquasmart.R
import com.example.aquasmart.databinding.AddReportBinding
import com.example.aquasmart.databinding.EditReportBinding
import com.example.aquasmart.models.Reports
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class DialogAddReport(
    val onNewReportDialog: (Reports) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = requireActivity().layoutInflater
        val viewDialogAddReport = inflater.inflate(R.layout.add_report, null)
        val binding = AddReportBinding.bind(viewDialogAddReport)

        return MaterialAlertDialogBuilder(requireActivity())
            .setView(viewDialogAddReport)
            .setTitle("Agregar Informe")
            .setMessage("Agregue un nuevo informe")
            .setIcon(R.drawable.baseline_add_circle_outline_24)
            .setPositiveButton("Aceptar") { _, _ ->
                val newReport = recoverDataLayout(binding)
                if (newReport.name.isEmpty() ||
                    newReport.clientName.isEmpty() ||
                    newReport.date.isEmpty() ||
                    newReport.description.isEmpty() ||
                    newReport.image.isEmpty()
                ) {
                    Toast.makeText(requireContext(), "Algún campo está vacío", Toast.LENGTH_LONG)
                        .show()
                } else {
                    onNewReportDialog(newReport)
                    dismiss() // Cierra el diálogo al aceptar
                }
            }
            .setNegativeButton("Cancelar") { _, _ -> dismiss() }
            .create()
    }

    private fun recoverDataLayout(binding: AddReportBinding): Reports {
        return Reports(
            name = binding.etReportName.text.toString().ifEmpty { "" },
            clientName = binding.etClientName.text.toString().ifEmpty { "" },
            date = binding.editTextDate.text.toString().ifEmpty { "" },
            description = binding.editTextMultiDescription.text.toString().ifEmpty { "" },
            image = binding.editTextImageUrl.toString().ifEmpty { "" }
        )
    }
}