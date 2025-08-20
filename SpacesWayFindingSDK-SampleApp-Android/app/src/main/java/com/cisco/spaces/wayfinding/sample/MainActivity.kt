package com.cisco.spaces.wayfinding.sample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.FragmentActivity
import com.cisco.spaces.wayfinding.sample.handlers.CustomMapExitHandler
import com.cisco.spaces.wayfinding.sample.handlers.CustomMapEventsHandler
import com.cisco.spaces.wayfinding.sample.handlers.CustomPathFindingEventsHandler
import com.ciscospaces.wayfinding.app.configuration.MapWidgetConfiguration
import com.ciscospaces.wayfinding.app.MapWidgetFragment
import com.ciscospaces.wayfinding.app.enums.LoggerLevel
import com.ciscospaces.wayfinding.app.SpacesWayFinding
import com.ciscospaces.wayfinding.app.enums.SpacesRegion
import com.ciscospaces.wayfinding.app.enums.SpacesWayFindingState
import com.ciscospaces.wayfinding.app.models.MapWidgetTheme
import com.ciscospaces.wayfinding.app.models.SpacesBuilding
import com.ciscospaces.wayfinding.app.models.SpacesWayFindingParams


class MainActivity : FragmentActivity(), BuildingParamsFragment.Listener {


    private val progressFragment = ProgressFragment()

    private lateinit var buildingParamsFragment: BuildingParamsFragment

    private val spacesWayFinding = SpacesWayFinding(
        "vIcsaUQgiHLoANVw_3r0SBtE28HsHwFfL6JkvvVMVcI",
        SpacesRegion.IO
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupOnBackPressed()
        setupWindowInsets()

        showLoading("Loading buildings...")

        loadInitialData()
    }

    private fun loadInitialData() {
        spacesWayFinding.getAllBuildings { spacesBuildings, error ->
            runOnUiThread {
                if (error != null) {
                    progressFragment.progressDescription = error
                    return@runOnUiThread
                }
                if (spacesBuildings.isNullOrEmpty()) {
                    progressFragment.progressDescription = "No buildings found"
                    return@runOnUiThread
                }
                buildingParamsFragment = BuildingParamsFragment(spacesBuildings)
                showBuildings()
            }
        }
    }

    /**
     * Fix edge to edge layout
     */
    private fun setupWindowInsets() {
        val fragmentContainer = findViewById<View>(R.id.fragment_container)
        ViewCompat.setOnApplyWindowInsetsListener(fragmentContainer) { v, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )

            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = bars.top
                leftMargin = bars.left
                rightMargin = bars.right
                bottomMargin = bars.bottom
            }
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun setupOnBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val spacesMapWidgetFragment =
                    supportFragmentManager.findFragmentById(R.id.fragment_container) as? MapWidgetFragment
                        ?: run {
                            finish()
                            return
                        }
                if (spacesMapWidgetFragment.isVisible) {
                    showBuildings()
                } else {
                    finish()
                }
            }
        })
    }

    private fun showLoading(description: String = "Loading...") {
        progressFragment.progressDescription = description
        if (!progressFragment.isAdded) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, progressFragment).commit()
        }
    }

    fun showBuildings() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, buildingParamsFragment)
            .commit()
    }

    /**
     * BuildingParamsFragment.Listener implementation
     */
    override fun onBuildingSelected(building: SpacesBuilding) {
        supportFragmentManager.beginTransaction().remove(buildingParamsFragment).commit()
//        showLoading("Initializing...")
        val params = SpacesWayFindingParams(building)
        params.loggerLevel = LoggerLevel.VERBOSE
        spacesWayFinding.selectBuilding(applicationContext, params) { state ->
            handleStateUpdate(state)
            runOnUiThread {
                showLoading(state.name)

            }
        }
    }

    private fun handleStateUpdate(state: SpacesWayFindingState) {
        runOnUiThread {
            Log.d("SpacesState", state.name)
            progressFragment.progressDescription = state.name
            if (state.intValue < 0) { // Failure to start the SDK
                // show buildings after 3 sec so that user can see the error message
                Handler(Looper.getMainLooper()).postDelayed({
                    showBuildings()
                }, 3000)
                return@runOnUiThread
            }
            if (state == SpacesWayFindingState.RUNNING) {
                showMapWidget()
                return@runOnUiThread
            }
        }
    }

    private fun showMapWidget() {
        setupMapTheme()
        val config = MapWidgetConfiguration.defaultConfiguration()
        config.isJoystickEnabled = true
        config.isOnboardingEnabled = true


        MapWidgetFragment.newInstance(
            supportFragmentManager,
            R.id.fragment_container,
            config
        ) { widget ->
            arrangeMapWidgetHandlers(widget)

            Handler(Looper.getMainLooper()).postDelayed({
                spacesWayFinding.getPoIs()

                spacesWayFinding.getFloors()
                 val level = spacesWayFinding.getFloor(3)
                if (level != null) {
                    widget.showFloor(level)
                }
            }, 5000)

        }
    }

    /**
     * Sets up the theme for the MapWidgetFragment
     * Defines colors used throughout the map interface
     */
    private fun setupMapTheme() {
        val theme = MapWidgetTheme(
            alertColor = "#28CC7A",
            backgroundColor = "#2D3134",
            foregroundColor = "#FFFFFF",
            accentColor = "#00BCEB",
            successColor = "#28CC7A",
            dangerColor = "#E43458",
            infoColor = "#FFFFFF"
        )
        MapWidgetFragment.theme = theme
    }

    private fun arrangeMapWidgetHandlers(mapWidget: MapWidgetFragment) {
        mapWidget.mapEventsHandler = CustomMapEventsHandler(mapWidget, spacesWayFinding)
        mapWidget.exitButtonEventsHandler = CustomMapExitHandler(this)
        mapWidget.pathFindingEventsHandler = CustomPathFindingEventsHandler(mapWidget)
    }
}