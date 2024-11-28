package com.example.aquasmart.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aquasmart.models.Reports
import com.example.aquasmart.databinding.ItemReportBinding

class ViewHolderReports(

    private val binding: ItemReportBinding,
    val deleteOnClick: (Int) -> Unit

) : RecyclerView.ViewHolder(binding.root) {

    fun render(report: Reports) {

        binding.textViewTitle.text = report.name
        binding.textViewSubtitle.text = report.date.toString()
        binding.textViewBody.text = report.clientName

        binding.ivReport.post {
            Glide.with(itemView.context)
                .load(report.image)
                //.override(Target.SIZE_ORIGINAL, itemView.height)
                .centerCrop()
                .into(binding.ivReport)
        }

        binding.floatButtonDelete.setOnClickListener {
            deleteOnClick(adapterPosition)
        }
    }
}

