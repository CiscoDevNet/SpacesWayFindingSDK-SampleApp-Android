package com.cisco.spaces.wayfinding.sample.handlers

import android.app.Activity
import android.util.Log
import com.ciscospaces.wayfinding.app.MapFragment
import com.ciscospaces.wayfinding.app.MapWidgetFragment
import com.ciscospaces.wayfinding.app.SpacesWayFinding
import com.ciscospaces.wayfinding.app.handlers.MapEventsHandler
import com.ciscospaces.wayfinding.app.models.PoI

/**
 * Custom implementation of the MapEventsHandler to handle map-related events
 * This class provides custom behavior for various map interactions such as
 * loading events and point of interest (PoI) taps.
 *
 * @param mapWidgetFragment The MapWidgetFragment instance that this handler is attached to
 */
class CustomMapEventsHandler(mapWidgetFragment: MapWidgetFragment, private val spacesWayFinding: SpacesWayFinding) :
    MapEventsHandler(mapWidgetFragment) {

    /**
     * Called when the map begins loading
     * This can be used to show loading indicators or prepare the UI
     *
     * @param mapFragment The MapFragment that started loading
     */
    override fun mapDidStartLoading(mapFragment: MapFragment) {
        super.mapDidStartLoading(mapFragment)
        Log.d("CustomMapEventsHandler", "mapDidStartLoading")
    }

    /**
     * Called when the map finishes loading
     * This can be used to hide loading indicators or initialize map interactions
     *
     * @param mapFragment The MapFragment that finished loading
     */
    override fun mapDidEndLoading(mapFragment: MapFragment) {
        super.mapDidEndLoading(mapFragment)
        Log.d("CustomMapEventsHandler", "mapDidEndLoading")
        spacesWayFinding.getPoIs()
    }

    /**
     * Called when a user taps on a Point of Interest (PoI) on the map
     * This can be used to show details about the selected PoI or trigger navigation
     *
     * @param mapFragment The MapFragment where the tap occurred
     * @param poi The Point of Interest that was tapped
     */
    override fun mapDidReceiveTapOnPoi(mapFragment: MapFragment, poi: PoI) {
        super.mapDidReceiveTapOnPoi(mapFragment,poi)
        Log.d("CustomMapEventsHandler", "mapDidReceiveTapOnPoi: ${poi.name}")
    }
}
