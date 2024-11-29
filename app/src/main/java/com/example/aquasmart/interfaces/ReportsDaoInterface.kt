package com.example.aquasmart.interfaces

import com.example.aquasmart.models.Reports

/**
 * Interfaz Reports Dao
 */
interface ReportsDaoInterface {

    /**
     * Método para obtener la lista de reportes
     */
    fun getReports(): List<Reports>
}