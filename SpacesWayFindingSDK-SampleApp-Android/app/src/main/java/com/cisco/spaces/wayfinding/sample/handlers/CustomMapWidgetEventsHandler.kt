package com.cisco.spaces.wayfinding.sample.handlers

import android.util.Log
import com.ciscospaces.wayfinding.app.MapWidgetFragment
import com.ciscospaces.wayfinding.app.handlers.MapWidgetEventsHandler
import org.json.JSONArray
import org.json.JSONObject

/**
 * Handles map-widget lifecycle events from the custom SDK wrapper.
 */
class CustomMapWidgetEventsHandler(
    private val mapWidgetFragment: MapWidgetFragment
) : MapWidgetEventsHandler(mapWidgetFragment) {

    private var customMapContentAdded = false

    override fun onDidStartLoading(identifier: String) {
        super.onDidStartLoading(identifier)
        Log.d("CustomMapWidgetEventsHandler", "onDidStartLoading: $identifier")
    }

    override fun onDidEndLoading(error: String?) {
        super.onDidEndLoading(error)
        Log.d("CustomMapWidgetEventsHandler", "onDidEndLoading: $error")

        if (error == null && !customMapContentAdded) {
            customMapContentAdded = true
            addCustomMapContent()
        }
    }

    override fun onFailure(error: String?) {
        super.onFailure(error)
        Log.e("CustomMapWidgetEventsHandler", "onFailure: $error")
    }

    private fun addCustomMapContent() {
        runCatching {
            addGeoJsonSource()
            addSymbolLayer()
            addHeatMapLayer()
            addMarkers()
        }.onFailure { throwable ->
            customMapContentAdded = false
            Log.e("CustomMapWidgetEventsHandler", "Failed to add custom map content", throwable)
        }
    }

    private fun addGeoJsonSource() {
        val featuresJsonArray = JSONArray().apply {
            put(createPointFeature(-122.38728110012187, 37.7703786952669, "Marker 1", 1, 1))
            put(createPointFeature(-122.3875076296718, 37.77036141591536, "Marker 2", 1, 2))
        }

        val featureCollection = JSONObject().apply {
            put("type", "FeatureCollection")
            put("features", featuresJsonArray)
        }

        val geoJsonSource = JSONObject().apply {
            put("type", "geojson")
            put("data", featureCollection)
        }

        mapWidgetFragment.addSource(SAMPLE_SOURCE_ID, geoJsonSource)
    }

    private fun createPointFeature(
        longitude: Double,
        latitude: Double,
        name: String,
        magnitude: Int,
        level: Int
    ): JSONObject {
        val geometry = JSONObject().apply {
            put("type", "Point")
            put("coordinates", JSONArray().apply {
                put(longitude)
                put(latitude)
            })
        }

        return JSONObject().apply {
            put("type", "Feature")
            put("geometry", geometry)
            put("properties", JSONObject().apply {
                put("name", name)
                put("magnitude", magnitude)
                put("lvl", level)
            })
        }
    }

    private fun addSymbolLayer() {
        val layer = JSONObject().apply {
            put("id", SAMPLE_SYMBOL_LAYER_ID)
            put("type", "symbol")
            put("source", SAMPLE_SOURCE_ID)
            put("minzoom", 17)
            put("maxzoom", 24)
            put("layout", JSONObject().apply {
                put("icon-image", "Restroom-n")
                put("icon-allow-overlap", true)
                put("text-field", JSONArray().apply {
                    put("get")
                    put("name")
                })
                put("text-offset", JSONArray().apply {
                    put(0)
                    put(1.2)
                })
                put("text-anchor", "top")
            })
            put("paint", JSONObject().apply {
                put("text-color", "#FFFFFF")
                put("text-halo-color", "#000000")
                put("text-halo-width", 1)
            })
        }

        mapWidgetFragment.addLayer(layer)
    }

    private fun addHeatMapLayer() {
        val heatMapLayer = JSONObject().apply {
            put("id", SAMPLE_HEATMAP_LAYER_ID)
            put("type", "heatmap")
            put("source", SAMPLE_SOURCE_ID)
            put("minzoom", 10)
            put("maxzoom", 22)
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
                    put(10)
                    put(1)
                    put(22)
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
                    put(10)
                    put(2)
                    put(22)
                    put(20)
                })
                put("heatmap-opacity", 0.8)
            })
        }

        mapWidgetFragment.addLayer(heatMapLayer)
    }

    private fun addMarkers() {
        mapWidgetFragment.addMarkerLayer(SAMPLE_MARKER_LAYER_ID) { markerLayer ->
            markerLayer.setIcon(
                "https://cdn.pixabay.com/photo/2014/04/03/10/03/google-309740_960_720.png",
                JSONObject().apply {
                    put("sdf", false)
                }
            )

            markerLayer.setMarkers(JSONArray().apply {
                put(createMarker(-122.38728110012187, 37.7703786952669, "Marker 1", 1))
                put(createMarker(-122.3875076296718, 37.77036141591536, "Marker 2", 2))
            })
            markerLayer.setPaint(JSONObject().apply {
                put("icon-opacity", 0.8)
                put("text-color", "#FFFFFF")
                put("text-halo-color", "#000000")
                put("text-halo-width", 1)
                put("text-field", "{name}")
            })
            markerLayer.setLayout(JSONObject().apply {
                put("icon-size", 0.5)
                put("icon-allow-overlap", true)
            })
            markerLayer.show()
        }
    }

    private fun createMarker(longitude: Double, latitude: Double, name: String, level: Int): JSONObject {
        return JSONObject().apply {
            put("coordinates", JSONArray().apply {
                put(longitude)
                put(latitude)
            })
            put("name", name)
            put("lvl", level)
        }
    }

    companion object {
        private const val SAMPLE_SOURCE_ID = "sample-symbol-source"
        private const val SAMPLE_SYMBOL_LAYER_ID = "sample-symbol-layer"
        private const val SAMPLE_HEATMAP_LAYER_ID = "sample-heatmap-layer"
        private const val SAMPLE_MARKER_LAYER_ID = "sample-marker-layer"
    }
}
