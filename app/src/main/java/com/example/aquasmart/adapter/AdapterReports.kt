package com.example.aquasmart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aquasmart.models.Reports
import com.example.aquasmart.databinding.ItemReportBinding

/**
 * Clase Adapter Reports para el adapter del RecyclerView de Reportes
 */
class AdapterReports(

    var listReports: MutableList<Reports>,
    var deleteOnClick: (Int) -> Unit

) : RecyclerView.Adapter<ViewHolderReports>() {

    /**
     * Método que infla la vista del CardView de Reportes.
     *
     * @param parent
     * @param viewType
     * @return La vista inflada con los métodos asociados
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderReports {

        val binding = ItemReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderReports(binding, deleteOnClick)
    }

    /**
     * Método que devuelve el tamaño de la lista de Reprtes
     */
    override fun getItemCount(): Int = listReports.size

    /**
     * Método que renderiza ViewHolder teniendo encuenta la posición de la lista de Reportes
     * @param ViewHolderReports
     * @param position
     */
    override fun onBindViewHolder(holder: ViewHolderReports, position: Int) {
        holder.render(listReports[position])
    }


}