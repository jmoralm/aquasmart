package com.example.aquasmart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aquasmart.models.Reports
import com.example.aquasmart.databinding.ItemReportBinding

class AdapterReports(
    var listReports: MutableList<Reports>,
    var deleteOnClick: (Int) -> Unit
) : RecyclerView.Adapter<ViewHolderReports>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderReports {

        val binding = ItemReportBinding.inflate(LayoutInflater.from(parent.context), parent , false)


        return ViewHolderReports(binding, deleteOnClick)
    }

    override fun getItemCount(): Int = listReports.size

    override fun onBindViewHolder(holder: ViewHolderReports, position: Int) {
        holder.render(listReports[position])
    }


}