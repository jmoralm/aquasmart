package com.example.aquasmart.dao

import com.example.aquasmart.interfaces.ReportsDaoInterface
import com.example.aquasmart.models.Reports
import com.example.aquasmart.repository.Repository

/**
 * Clase ReportsDaoImpl que implementa la interfaz ReportsDaoInterface
 */
class ReportsDaoImpl private constructor() : ReportsDaoInterface {

    /**
     * compaion objetct que contiene patron singleton de ReportsDaoImpl
     */
    companion object {
        val myDao: ReportsDaoImpl by lazy {
            ReportsDaoImpl()
        }
    }

    /**
     * Método que devuelve la lista de Reportes del repositorio.
     */
    override fun getReports(): List<Reports> = Repository.listReports

}