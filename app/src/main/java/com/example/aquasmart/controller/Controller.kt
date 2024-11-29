package com.example.aquasmart.controller

import android.content.Context
import com.example.aquasmart.adapter.AdapterReports
import com.example.aquasmart.MainActivity
import com.example.aquasmart.dao.ReportsDaoImpl
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

    init {
        initData()
    }

    /**
     * Método que inicializa la lista de reportes y e inicializa el adaptador
     * del recyclerView
     */
    private fun initData() {

        listReports = ReportsDaoImpl.myDao.getReports().toMutableList()

        adapter = AdapterReports(listReports,
            { position ->
                deleteReport(position)
            })
    }

    /**
     * Método que setea el adaptar del RecyclerView asociado a la vista
     */
    fun setAdapter() {
        val myActivity = context as MainActivity
        myActivity.binding.rvReports.adapter = adapter
    }

    /**
     * Método para borrar un Reporte.
     * También notifica de que un item ha sido eliminado.
     */
    private fun deleteReport(position: Int) {
        listReports.removeAt(position)
        adapter.notifyItemRemoved(position)
        adapter.notifyItemRangeChanged(position, listReports.size)
    }

}