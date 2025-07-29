package com.cisco.spaces.wayfinding.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class ProgressFragment: Fragment() {

    private lateinit var textViewProgressDescription: TextView

    var progressDescription: String = "Loading..."
        set(value) {
            field = value
            if (::textViewProgressDescription.isInitialized.not()) return
            textViewProgressDescription.text = value
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_progress, container, false)
        textViewProgressDescription = view.findViewById(R.id.textViewProgressDescription)
        textViewProgressDescription.text = progressDescription
        return view
    }
}