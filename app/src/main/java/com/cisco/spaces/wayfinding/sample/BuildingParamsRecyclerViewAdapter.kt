package com.cisco.spaces.wayfinding.sample

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.cisco.spaces.wayfinding.sample.databinding.FragmentBuildingParamsBinding
import com.ciscospaces.wayfinding.app.models.SpacesBuilding

/**
 * [RecyclerView.Adapter] that can display a [com.ciscospaces.wayfinding.app.models.SpacesBuilding].
 */
class BuildingParamsRecyclerViewAdapter(
    private val listBuildingParams: List<SpacesBuilding>,
    private val listener: BuildingParamsFragment.Listener
) : RecyclerView.Adapter<BuildingParamsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentBuildingParamsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val buildingParams = listBuildingParams[position]
        holder.buildingParamsNameView.text = buildingParams.name
        holder.itemView.setOnClickListener {
            listener.onBuildingSelected(buildingParams)
        }
    }

    override fun getItemCount(): Int = listBuildingParams.size

    inner class ViewHolder(binding: FragmentBuildingParamsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val buildingParamsNameView: TextView = binding.buildingParamsName

        override fun toString(): String {
            return super.toString() + " '" + buildingParamsNameView.text + "'"
        }
    }

}