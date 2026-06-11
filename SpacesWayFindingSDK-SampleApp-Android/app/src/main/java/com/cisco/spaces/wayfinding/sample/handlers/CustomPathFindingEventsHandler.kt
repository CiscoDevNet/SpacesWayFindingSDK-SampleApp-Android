package com.cisco.spaces.wayfinding.sample.handlers

import android.util.Log
import com.ciscospaces.wayfinding.app.MapWidgetFragment
import com.ciscospaces.wayfinding.app.handlers.PathFindingEventsHandler

/**
 * Handles wayfinding lifecycle events from the custom SDK wrapper.
 */
class CustomPathFindingEventsHandler(
    mapWidget: MapWidgetFragment
) : PathFindingEventsHandler(mapWidget) {

    /**
     * Called when the user taps the close button during navigation
     * This can be used to clean up resources or update UI elements when navigation ends
     */
    override fun pathFindingDidTapClose() {
        super.pathFindingDidTapClose()
        Log.d("CustomPathFindingEventsHandler", "pathFindingDidTapClose")
    }

    override fun wayfindingDone() {
        super.wayfindingDone()
        Log.d("CustomPathFindingEventsHandler", "wayfindingDone")
    }

    override fun wayfindingDidShown() {
        super.wayfindingDidShown()
        Log.d("CustomPathFindingEventsHandler", "wayfindingDidShown")
    }
}
