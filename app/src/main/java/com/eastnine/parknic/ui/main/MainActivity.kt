package com.eastnine.parknic.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.eastnine.domain.dto.ParkingDto
import com.eastnine.parknic.R
import com.eastnine.parknic.databinding.ActivityMainBinding
import com.eastnine.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    MapView.MapViewEventListener {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupMap()
        setupListener()
    }

    private fun setupBinding() {
        binding.viewModel = viewModel
    }

    private fun setupMap() {
        binding.mainMapView.run {
            setDefaultCurrentLocationMarker()
            setShowCurrentLocationMarker(true)
        }
    }

    private fun setupListener() {
        binding.mainMapView.setMapViewEventListener(this)

        viewModel.setOnSplashDataListener(object: OnMainDataListener {
            override fun getParking(parkingList: List<ParkingDto>) {
                binding.mainMapView.run {
                    removeAllPOIItems()

                    for (parking in parkingList) {
                        val marker = MapPOIItem()
                        marker.run {
                            itemName = parking.parkingName
                            mapPoint = MapPoint.mapPointWithGeoCoord(parking.lat, parking.lng)
                            markerType = MapPOIItem.MarkerType.RedPin
                        }
                        addPOIItem(marker)
                    }
                }
            }
        })
    }

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onMapViewInitialized(mapView: MapView?) {
        mapView?.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
    }

    override fun onMapViewCenterPointMoved(mapView: MapView?, point: MapPoint?) = Unit
    override fun onMapViewZoomLevelChanged(mapView: MapView?, level: Int) = Unit
    override fun onMapViewSingleTapped(mapView: MapView?, point: MapPoint?) = Unit
    override fun onMapViewDoubleTapped(mapView: MapView?, point: MapPoint?) = Unit
    override fun onMapViewLongPressed(mapView: MapView?, point: MapPoint?) = Unit
    override fun onMapViewDragStarted(mapView: MapView?, point: MapPoint?) = Unit
    override fun onMapViewDragEnded(mapView: MapView?, point: MapPoint?) {
        viewModel.setSearchLocationButtonVisibility(true)
    }
    override fun onMapViewMoveFinished(mapView: MapView?, point: MapPoint?) {
        if (viewModel.mapCenterPoint == null) {
            mapView?.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
            point?.let {
                viewModel.getParking(it.mapPointGeoCoord.latitude, it.mapPointGeoCoord.longitude)
            }
        }

        viewModel.mapCenterPoint = point
    }
}