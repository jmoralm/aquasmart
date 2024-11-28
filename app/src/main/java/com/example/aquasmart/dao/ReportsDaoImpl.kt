package com.example.aquasmart.dao

import com.example.aquasmart.interfaces.ReportsDaoInterface
import com.example.aquasmart.models.Reports
import com.example.aquasmart.repository.Repository

class ReportsDaoImpl private constructor() : ReportsDaoInterface {

    companion object {
        val myDao : ReportsDaoImpl by lazy {
            ReportsDaoImpl()
        }
    }

    override fun getReports(): List<Reports> = Repository.listReports

}