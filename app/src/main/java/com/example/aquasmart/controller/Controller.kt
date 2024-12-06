package com.example.aquasmart.controller

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aquasmart.MainActivity
import com.example.aquasmart.adapter.AdapterReports
import com.example.aquasmart.dao.ReportsDaoImpl
import com.example.aquasmart.dialog.DialogAddReport
import com.example.aquasmart.dialog.DialogDeleteReport
import com.example.aquasmart.dialog.DialogEditReport
import com.example.aquasmart.models.Reports

/**
 * Clase Controller.
 * Contiene la lógica para borrar un reporte.
 * Setea el adapter del RecyclerView de Reportes
 * y la lista mutable de todos los reportes.
 */
class Controller(private val context: Context) {

    private lateinit var listReports: MutableList<Reports>
    private lateinit var adapter: AdapterReports
    private lateinit var layoutManager: LinearLayoutManager

    init {
        initData()
        initClickListener()
    }

    /**
     * Método que inicializa la lista de reportes y e inicializa el adaptador
     * del recyclerView
     */
    private fun initData() {

        listReports = ReportsDaoImpl.myDao.getReports().toMutableList()

        layoutManager =
            ((context as MainActivity).binding.rvReports.layoutManager as LinearLayoutManager)

        adapter = AdapterReports(listReports,
            { position ->
                deleteReport(position)
            },
            { position ->
                updateReport(position)
            })

    }

    private fun updateReport(pos: Int) {

        val editDialog = DialogEditReport(listReports[pos])

        { editReport ->
            onEditReport(editReport, pos)
        }

        val myActivity = context as MainActivity
        editDialog.show(myActivity.supportFragmentManager, "Editamos un Reporte")
    }

    private fun deleteReport(pos: Int) {
        val deleteDialog = DialogDeleteReport(listReports[pos])

        { _ ->
            onDeleteReport(pos)
        }

        val myActivity = context as MainActivity
        deleteDialog.show(myActivity.supportFragmentManager, "Borramos un Reporte")
    }

    private fun onEditReport(editReport: Reports, pos: Int) {
        listReports.removeAt(pos)
        adapter.notifyItemRemoved(pos)
        listReports.add(pos, editReport)
        adapter.notifyItemInserted(pos)
        layoutManager.scrollToPositionWithOffset(pos, listReports.size - 1)
    }

    private fun onAddReport(newReport: Reports) {
        listReports.add(listReports.size, newReport)
        adapter.notifyItemInserted(listReports.lastIndex)
        layoutManager.scrollToPositionWithOffset(listReports.lastIndex, 20)
    }

    /**
     * Método para borrar un Reporte.
     * También notifica de que un item ha sido eliminado.
     */
    private fun onDeleteReport(position: Int) {
        listReports.removeAt(position)
        adapter.notifyItemRemoved(position)
        adapter.notifyItemRangeChanged(position, listReports.size)
    }


    private fun initClickListener() {
        val myActivity = context as MainActivity
        myActivity.binding.fabAddReport.setOnClickListener {
            addHotel()
        }
    }

    private fun addHotel() {
        val dialog = DialogAddReport() { report ->
            onAddReport(report)
        }

        val myActivity = context as MainActivity
        dialog.show(myActivity.supportFragmentManager, "Añadir Hotel")
    }

    /**
     * Método que setea el adaptar del RecyclerView asociado a la vista
     */
    fun setAdapter() {
        val myActivity = context as MainActivity
        myActivity.binding.rvReports.adapter = adapter
    }


}