package com.cisco.spaces.wayfinding.sample.handlers

import android.util.Log
import com.ciscospaces.wayfinding.app.MapFragment
import com.ciscospaces.wayfinding.app.MapWidgetFragment
import com.ciscospaces.wayfinding.app.SpacesWayFinding
import com.ciscospaces.wayfinding.app.handlers.MapEventsHandler
import com.ciscospaces.wayfinding.app.models.PoI
import com.ciscospaces.wayfinding.app.models.TappedLocation
import org.json.JSONArray
import org.json.JSONObject

/**
 * Handles map-level events such as PoI taps.
 */
class CustomMapEventsHandler(
        mapWidgetFragment: MapWidgetFragment
) : MapEventsHandler(mapWidgetFragment) {

    override fun mapDidReceiveTapOnPoi(mapFragment: MapFragment, poi: PoI) {
        super.mapDidReceiveTapOnPoi(mapFragment, poi)
        Log.d("CustomMapEventsHandler", "mapDidReceiveTapOnPoi: ${poi.name}")
    }

    override fun mapDidReceiveTap(mapFragment: MapFragment, tappedLocation: TappedLocation) {
        super.mapDidReceiveTap(mapFragment, tappedLocation)
    }

    override fun mapDidStartLoading(mapFragment: MapFragment) {
        super.mapDidStartLoading(mapFragment)
        Log.d("CustomMapEventsHandler", "onDidStartLoading")
    }

    override fun mapDidEndLoading(mapFragment: MapFragment) {
        super.mapDidEndLoading(mapFragment)
        Log.d("CustomMapEventsHandler", "onDidEndLoading")
    }
}
