package com.cisco.spaces.wayfinding.sample.handlers

import android.util.Log
import android.widget.TextView
import com.cisco.spaces.wayfinding.sample.MainActivity
import com.ciscospaces.wayfinding.app.MapWidgetFragment
import com.ciscospaces.wayfinding.app.handlers.ExitButtonEventsHandler

/**
 * Custom implementation of the ExitButtonEventsHandler to handle exit button interactions
 * This class manages what happens when a user taps the exit button on the map interface,
 * providing a clean transition back to the building selection screen.
 *
 * @param activity The MainActivity instance
 */
class CustomMapExitHandler(private val activity: MainActivity) : ExitButtonEventsHandler() {

    /**
     * Called when the user taps the exit button on the map
     * This implementation navigates the user back to the building selection screen
     * and ensures clean state for subsequent building selections
     *
     * @param mapWidgetFragment The MapWidgetFragment where the exit button was clicked
     * @param exitButton The TextView representing the exit button that was tapped
     */
    override fun onExitClicked(mapWidgetFragment: MapWidgetFragment, exitButton: TextView) {
        // Reset any state that might affect subsequent building selections
        Log.d("CustomMapExitHandler", "Exiting map and preparing for next building selection")

        activity.showBuildings()
    }
}