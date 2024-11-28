package com.example.aquasmart.interfaces

import com.example.aquasmart.models.Reports

interface ReportsDaoInterface {

    fun getReports(): List<Reports>
}