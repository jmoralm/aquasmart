package com.example.aquasmart.controller

import android.content.Context
import com.example.aquasmart.adapter.AdapterReports
import com.example.aquasmart.MainActivity
import com.example.aquasmart.dao.ReportsDaoImpl
import com.example.aquasmart.models.Reports

class Controller(private val context: Context) {

    private lateinit var listReports: MutableList<Reports>
    private lateinit var adapter : AdapterReports

    init {
        initData()
    }

    private fun initData() {

        listReports = ReportsDaoImpl.myDao.getReports().toMutableList()

        adapter = AdapterReports(listReports,
            {
                position -> deleteReport(position)
            })
    }

    fun setAdapter() {
        val myActivity = context as MainActivity
        myActivity.binding.rvReports.adapter = adapter
    }

    private fun deleteReport(position : Int) {
        listReports.removeAt(position)
        adapter.notifyItemRemoved(position)
        adapter.notifyItemRangeChanged(position, listReports.size)
    }

}