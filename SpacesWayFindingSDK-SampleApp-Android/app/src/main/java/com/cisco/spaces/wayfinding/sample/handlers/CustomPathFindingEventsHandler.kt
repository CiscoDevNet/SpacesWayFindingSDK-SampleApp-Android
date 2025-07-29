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
     * Called when the user taps the close button during navigation
     * This can be used to clean up resources or update UI elements when navigation ends
     */
    override fun pathFindingDidTapClose() {
        Log.d("CustomPathFindingEventsHandler", "pathFindingDidTapClose")
    }

    /**
     * Called when the user taps the close button while navigation is in progress
     * This can be used to show confirmation dialogs or handle premature navigation termination
     */
    override fun handleDidTapCloseWhileInProgress() {
        Log.d("CustomPathFindingEventsHandler", "handleDidTapCloseWhileInProgress")
    }

    /**
     * Called when the user taps the close button after reaching the destination
     * This can be used to show completion messages or reset the UI to pre-navigation state
     */
    override fun handleDidTapCloseWhenArrived() {
        Log.d("CustomPathFindingEventsHandler", "handleDidTapCloseWhenArrived")
    }
}