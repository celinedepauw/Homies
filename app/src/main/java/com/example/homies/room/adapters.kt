package com.example.homies.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homies.databinding.ItemBuyerBinding
import com.example.homies.databinding.ItemSalerBinding
import com.example.homies.room.project.Project

class BuyersAdapter() : ListAdapter<Project, BuyersHolder>(MyDiffUtil()) {
    lateinit var binding : ItemBuyerBinding
    var onClick : ((Project) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyersHolder {
        binding = ItemBuyerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyersHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyersHolder, position: Int) {
        val project : Project = getItem(position)
        val firstname = when{
            project.clientFirstname == ""   -> ""
            else                            -> "${project.clientFirstname.substring(0,1).uppercase()}${project.clientFirstname.substring(1)}"
        }
        val location = when{
            project.projectLocation == ""   -> ""
            else                            -> "${project.projectLocation.substring(0,1).uppercase()}${project.projectLocation.substring(1)}"
        }
        val priceStr = project.projectPrice.toString()
        val price = when{
            priceStr.length == 5 -> "${priceStr.substring(0,2)} ${priceStr.substring(2,5)}€"
            priceStr.length == 6 -> "${priceStr.substring(0,3)} ${priceStr.substring(3,6)}€"
            priceStr.length == 7 -> "${priceStr.substring(0,1)} ${priceStr.substring(1,4)} ${priceStr.substring(4,7)}€"
            else -> ""
        }

        holder.binding.apply{
            tvItemBuyerContact.text = "${project.clientName.uppercase()} $firstname"
            tvItemBuyerLocalization.text = location
            tvItemBuyerPrice.text =
                if(project.projectPrice == 0)
                    "Budget à définir"
                else
                    price
            if(project.projectType == "Terrain")
                tvItemBuyerSurface.text = if(project.projectLandSurface == 0) "" else "${project.projectLandSurface} m²"
            else
                tvItemBuyerSurface.text = if(project.projectSurface == 0) "" else "${project.projectSurface} m²"

            tvItemBuyerType.text =
                if(project.projectT == "")
                    "${project.projectType}"
                else
                    "${project.projectType} T${project.projectT}"
            clItemBuyer.setOnClickListener {
                onClick?.invoke(project)
            }
        }
    }
}

class SalersAdapter() : ListAdapter<Project, SalersHolder>(MyDiffUtil()) {
    lateinit var binding : ItemSalerBinding
    var onClick : ((Project) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalersHolder {
        binding = ItemSalerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SalersHolder(binding)
    }

    override fun onBindViewHolder(holder: SalersHolder, position: Int) {
        val project : Project = getItem(position)
        val firstname = when{
            project.clientFirstname == "" -> ""
            else -> "${project.clientFirstname.substring(0,1).uppercase()}${project.clientFirstname.substring(1)}"
        }
        val location = when{
            project.projectLocation == ""   -> ""
            else                            -> "${project.projectLocation.substring(0,1).uppercase()}${project.projectLocation.substring(1)}"
        }
        val priceStr = project.projectPrice.toString()
        val price = when{
            priceStr.length == 5 -> "${priceStr.substring(0,2)} ${priceStr.substring(2,5)}€"
            priceStr.length == 6 -> "${priceStr.substring(0,3)} ${priceStr.substring(3,6)}€"
            priceStr.length == 7 -> "${priceStr.substring(0,1)} ${priceStr.substring(1,4)} ${priceStr.substring(4,7)}€"
            else -> ""
        }

        holder.binding.apply{
            tvItemSalerContact.text = "${project.clientName.uppercase()} $firstname"
            tvItemSalerLocalization.text = location
            tvItemSalerPrice.text = when{
                project.projectPrice == 0                                   -> "Estimation à réaliser"
                project.projectPrice == 0 && project.projectSurface == 0    -> "Estimation à réaliser"
                project.projectPrice !== 0 && project.projectSurface !==0   -> "$price (${project.projectPrice / project.projectSurface}€/m²)"
                project.projectSurface == 0                                 -> "Prix au m² indisponible"
                else                                                        -> "Information non disponible"
            }
            if(project.projectType == "Terrain")
                tvItemSalerSurface.text = if(project.projectLandSurface == 0) "" else "${project.projectLandSurface} m²"
            else
                tvItemSalerSurface.text = if(project.projectSurface == 0) "" else "${project.projectSurface} m²"
            tvItemSalerType.text =
                if(project.projectT == "")
                    "${project.projectType}"
                else
                    "${project.projectType} T${project.projectT}"
            clItemSaler.setOnClickListener {
                onClick?.invoke(project)
            }
        }
    }
}

class MyDiffUtil :DiffUtil.ItemCallback<Project>() {
    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem.projectId == newItem.projectId
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem == newItem
    }
}

class BuyersHolder(val binding: ItemBuyerBinding) : RecyclerView.ViewHolder(binding.root)

class SalersHolder(val binding: ItemSalerBinding) : RecyclerView.ViewHolder(binding.root)
