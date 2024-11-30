package com.example.aquasmart.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.aquasmart.MainActivity
import com.example.aquasmart.R
import com.example.aquasmart.databinding.EditReportBinding
import com.example.aquasmart.models.Reports
import com.example.aquasmart.models.ReportsArguments


class DialogEditReport(
    val reportToUpdate: Reports,
    val updateReportDialog: (Reports) -> Unit
) : DialogFragment() {

    lateinit var activity: MainActivity

    val ARGUMENT_NAME: String = ReportsArguments.ARGUMENT_NAME
    val ARGUMENT_CLIENTNAME: String = ReportsArguments.ARGUMENT_CLIENTENAME
    val ARGUMENT_DATE: String = ReportsArguments.ARGUMENT_DATE
    val ARGUMENT_DESCRIPTION = ReportsArguments.ARGUMENT_DESCRIPTION
    // val ARGUMENT_IMAGE = ReportsArguments.ARGUMENT_IMAGE

    init {
        // Se prepara el Bundle para pasarle al dialogo
        val args = Bundle().apply {
            putString(ARGUMENT_NAME, reportToUpdate.name)
            putString(ARGUMENT_CLIENTNAME, reportToUpdate.clientName)
            putString(ARGUMENT_DATE, reportToUpdate.date.toString())
            putString(ARGUMENT_DESCRIPTION, reportToUpdate.description)
            // putString(ARGUMENT_IMAGE, reportToUpdate.image)
        }
        this.arguments = args
    }

    private fun setValuesIntoDialog(binding: EditReportBinding) {
        binding.etReportName.setText(reportToUpdate.name)
        binding.etClientName.setText(reportToUpdate.clientName)
        binding.editTextDate.setText(reportToUpdate.date)
        binding.editTextMultiDescription.setText(reportToUpdate.description)
    }

    private fun recoverDataLayout(binding: EditReportBinding): Reports {
        return Reports(
            name = binding.etReportName.text.toString().ifEmpty { "" },
            clientName = binding.etClientName.text.toString().ifEmpty { "" },
            date = binding.editTextDate.text.toString().ifEmpty { "" },
            description = binding.editTextMultiDescription.text.toString().ifEmpty { "" },
            image = "binding.tvImageUrl.text.toString()"
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = requireActivity().layoutInflater
        val viewDialogEditReport = inflater.inflate(R.layout.edit_report, null)
        val binding = EditReportBinding.bind(viewDialogEditReport)

        // Configurar datos iniciales
        setValuesIntoDialog(binding)

        return AlertDialog.Builder(requireActivity())
            .setView(viewDialogEditReport)
            .setPositiveButton("Aceptar") { _, _ ->
                val updatedReport = recoverDataLayout(binding) as Reports
                if (updatedReport.name.isEmpty() ||
                    updatedReport.clientName.isEmpty() ||
                    updatedReport.date.isEmpty() ||
                    updatedReport.description.isEmpty()
                ) {
                    Toast.makeText(requireContext(), "Algún campo está vacío", Toast.LENGTH_LONG).show()
                } else {
                    updateReportDialog(updatedReport)
                    dismiss() // Cierra el diálogo al aceptar
                }
            }
            .setNegativeButton("Cancelar") { _, _ -> dismiss() }
            .create()


        /*
        return (activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflater = it.layoutInflater

            val viewDialogEditReport = inflater.inflate(R.layout.edit_report, null)
            setValuesIntoDialog(viewDialogEditReport, this.arguments)

            builder.setView(viewDialogEditReport)
                .setPositiveButton("Aceptar",
                    DialogInterface.OnClickListener { dialog, id ->
                        val updateReport = recoverDataLayout(viewDialogEditReport) as Reports
                        if (
                            updateReport.name.isNullOrEmpty() ||
                            updateReport.clientName.isNullOrEmpty() ||
                            updateReport.date.isNullOrEmpty() ||
                            updateReport.description.isNullOrEmpty()
                            // updateReport.image.isNullOrEmpty()
                        ) {
                            Toast.makeText(activity, "Algún campo está vacío", Toast.LENGTH_LONG)
                                .show()
                            getDialog()?.cancel()
                        } else {
                            updateReportDialog(updateReport)
                        }
                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
                .create()


        } ?: throw IllegalStateException("Activity cannot be null")) as Dialog*/
    }

/*    private fun setValuesIntoDialog(viewDialogEditReport: View, arguments: Bundle?) {

        val binding = EditReportBinding.bind(viewDialogEditReport)
        if (arguments != null) {
            binding.etReportName.setText(arguments?.getString(ARGUMENT_NAME))
            binding.etClientName.setText(arguments?.getString(ARGUMENT_CLIENTNAME))
            binding.editTextDate.setText(arguments?.getString(ARGUMENT_DATE))
            binding.editTextMultiDescription.setText(arguments?.getString(ARGUMENT_DESCRIPTION))

        }
    }

    private fun recoverDataLayout(viewDialogEditReport: View): Any {

        val binding = EditReportBinding.bind(viewDialogEditReport)
        return Reports(
            binding.etReportName.text.toString(),
            binding.etClientName.text.toString(),
            binding.editTextDate.text.toString(),
            binding.editTextMultiDescription.text.toString(),
            binding.tvImageUrl.text.toString(),
        )

    }*/
}