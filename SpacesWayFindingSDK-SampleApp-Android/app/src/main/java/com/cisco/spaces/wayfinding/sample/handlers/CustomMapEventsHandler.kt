package com.cisco.spaces.wayfinding.sample.handlers

import android.util.Log
import com.ciscospaces.wayfinding.app.MapFragment
import com.ciscospaces.wayfinding.app.MapWidgetFragment
import com.ciscospaces.wayfinding.app.handlers.MapEventsHandler
import com.ciscospaces.wayfinding.app.models.PoI

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
}
