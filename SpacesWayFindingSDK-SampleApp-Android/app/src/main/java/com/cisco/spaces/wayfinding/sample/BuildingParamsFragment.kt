package com.cisco.spaces.wayfinding.sample

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ciscospaces.wayfinding.app.models.SpacesBuilding

/**
 * A fragment representing a list of Buildings.
 */
class BuildingParamsFragment(private val listBuildingParams: List<SpacesBuilding>?) : Fragment() {

    interface Listener {
        fun onBuildingSelected(building: SpacesBuilding)
    }

    private lateinit var listener: Listener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_building_params_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = listBuildingParams?.let { BuildingParamsRecyclerViewAdapter(it, listener) }
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnBuildingSelectedListener")
        }
    }
}