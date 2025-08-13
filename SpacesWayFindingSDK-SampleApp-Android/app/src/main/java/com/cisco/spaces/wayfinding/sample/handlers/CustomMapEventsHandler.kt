package com.cisco.spaces.wayfinding.sample.handlers

import android.util.Log
import com.ciscospaces.wayfinding.app.MapFragment
import com.ciscospaces.wayfinding.app.MapWidgetFragment
import com.ciscospaces.wayfinding.app.SpacesWayFinding
import com.ciscospaces.wayfinding.app.handlers.MapEventsHandler
import com.ciscospaces.wayfinding.app.models.PoI
import org.json.JSONArray
import org.json.JSONObject

/**
 * Custom implementation of the MapEventsHandler to handle map-related events
 * This class provides custom behavior for various map interactions such as
 * loading events and point of interest (PoI) taps.
 *
 * @param mapWidgetFragment The MapWidgetFragment instance that this handler is attached to
 */
class CustomMapEventsHandler(
    private val mapWidgetFragment: MapWidgetFragment,
    private val spacesWayFinding: SpacesWayFinding
) :
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

        addGeoJsonSource()

        val layer = JSONObject().apply {
            put("id", "sample-layer")
            put("type", "symbol")
            put("source", "source_ptr")
            put("source-layer", "maps-layer")
            put("minzoom", 17)
            put("maxzoom", 24)
            put("layout", JSONObject().apply {
                put("text-field", "sample-icon")
            })
            put("paint", JSONObject().apply {
                put("icon-image", "Restroom-n")
            })
        }
        mapWidgetFragment.addLayer(layer) // Add a symbol layer to the map
        addHeatMapLayer()
        addMarkers()


    }

    private fun addGeoJsonSource(){
        val featuresJsonArray = JSONArray()
        val geometry1 = JSONObject().apply {
            put("type", "Point")
            put("coordinates", JSONArray().apply {
                put(-122.38728110012187) // SFO12 Coordinates
                put(37.7703786952669)
            })
        }

        val feature1 = JSONObject().apply {
            put("type", "Feature")
            put("geometry", geometry1)
            put("properties", JSONObject())
        }
        featuresJsonArray.put(feature1)

        val geometry2 = JSONObject().apply {
            put("type", "Point")
            put("coordinates", JSONArray().apply {
                put(-122.3875076296718) // SFO12 Coordinates
                put(37.77036141591536)
            })
        }

        val feature2 = JSONObject().apply {
            put("type", "Feature")
            put("geometry", geometry2)
            put("properties", JSONObject())
        }
        featuresJsonArray.put(feature2)

        val featureCollection = JSONObject().apply {
            put("type", "FeatureCollection")
            put("features", featuresJsonArray)
        }
        val geoJsonSource = JSONObject().apply {
            put("type", "geojson")
            put("data", featureCollection.toString())
        }
        mapWidgetFragment.addSource("symbol-source", geoJsonSource)
    }

    private fun addHeatMapLayer(){
        val heatMapLayer = JSONObject().apply {
            put("id", "sample-layer")
            put("type", "heatmap")
            put("source", "symbol-source")
            put("source-layer", "maps-layer")
            put("minzoom", 10)
            put("maxzoom", 22)
            put("layout", JSONObject().apply {
                put("visibility", "visible")
            })
            put("paint", JSONObject().apply {
                put("heatmap-weight", JSONArray().apply {
                    put("interpolate")
                    put(JSONArray().apply { put("linear") })
                    put(JSONArray().apply {
                        put("get")
                        put("magnitude")
                    })
                    put(0)
                    put(0)
                    put(6)
                    put(1)
                })

                put("heatmap-intensity", JSONArray().apply {
                    put("interpolate")
                    put(JSONArray().apply { put("linear") })
                    put(JSONArray().apply { put("zoom") })
                    put(0)
                    put(1)
                    put(9)
                    put(3)
                })

                put("heatmap-color", JSONArray().apply {
                    put("interpolate")
                    put(JSONArray().apply { put("linear") })
                    put(JSONArray().apply { put("heatmap-density") })
                    put(0)
                    put("rgba(33,102,172,0)")
                    put(0.2)
                    put("rgb(103,169,207)")
                    put(0.4)
                    put("rgb(209,229,240)")
                    put(0.6)
                    put("rgb(253,219,199)")
                    put(0.8)
                    put("rgb(239,138,98)")
                    put(1)
                    put("rgb(178,24,43)")
                })

                put("heatmap-radius", JSONArray().apply {
                    put("interpolate")
                    put(JSONArray().apply { put("linear") })
                    put(JSONArray().apply { put("zoom") })
                    put(0)
                    put(2)
                    put(9)
                    put(20)
                })

                put("heatmap-opacity", 0.8)
            })
        }
        mapWidgetFragment.addLayer(heatMapLayer)
    }

    private fun addMarkers(){
        mapWidgetFragment.addMarkerLayer("marker") { markerLayer ->
            markerLayer.setIcon("https://cdn.pixabay.com/photo/2014/04/03/10/03/google-309740_960_720.png",
                JSONObject().apply {
                    put("sdf", false)
                })

            markerLayer.setMarkers(JSONArray().apply {
                put(JSONObject().apply {
                    put("coordinates", JSONArray().apply {
                        put(-122.38728110012187) // SFO12 Coordinates
                        put(37.7703786952669)
                    })
                    put("name", "Marker 1")
                })
                put(JSONObject().apply {
                    put("coordinates", JSONArray().apply {
                        put(-122.3875076296718) // SFO12 Coordinates
                        put(37.77036141591536)
                    })
                    put("name", "Marker 2")
                })
            })
            markerLayer.setPaint(JSONObject().apply {
                put("icon-color", "green")
                put("icon-opacity", 0.8)
                put("text-field", "{name}")
            })
            markerLayer.setLayout(JSONObject().apply {
                put("icon-size", 0.5)
                put("icon-allow-overlap", true)
            })
            markerLayer.hide()
            markerLayer.show()
        }
    }

    /**
     * Called when a user taps on a Point of Interest (PoI) on the map
     * This can be used to show details about the selected PoI or trigger navigation
     *
     * @param mapFragment The MapFragment where the tap occurred
     * @param poi The Point of Interest that was tapped
     */
    override fun mapDidReceiveTapOnPoi(mapFragment: MapFragment, poi: PoI) {
        super.mapDidReceiveTapOnPoi(mapFragment, poi)
        Log.d("CustomMapEventsHandler", "mapDidReceiveTapOnPoi: ${poi.name}")
    }
}
