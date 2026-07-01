package com.cisco.spaces.wayfinding.sample.handlers

import android.util.Log
import com.ciscospaces.wayfinding.app.MapWidgetFragment
import com.ciscospaces.wayfinding.app.handlers.PathFindingEventsHandler

/**
 * Custom implementation of the PathFindingEventsHandler to handle navigation-related events
 * This class manages user interactions during wayfinding, such as
 * cancellation of route guidance and handling arrival events.
 *
 * @param mapWidget The MapWidgetFragment instance that this handler is attached to
 */
class CustomPathFindingEventsHandler(
    mapWidget: MapWidgetFragment
) : PathFindingEventsHandler(mapWidget) {

    /**
     * Called when the close button is tapped during wayfinding.
     * This can be used to show confirmation dialogs or handle premature navigation termination
     */
    override fun wayfindingDidTapClose() {
        Log.d("CustomPathFindingEventsHandler", "wayfindingDidTapClose")
    }

    /**
     * Called when wayfinding is completed.
     * This can be used to clean up resources or update UI elements when navigation ends
     */
    override fun wayfindingDone() {
        Log.d("CustomPathFindingEventsHandler", "wayfindingDone")
    }

    /**
     * Called when the wayfinding UI is shown.
     * This can be used to update UI state or perform setup when wayfinding starts.
     */
    override fun wayfindingDidShown() {
        Log.d("CustomPathFindingEventsHandler", "wayfindingDidShown")
    }

}