package com.eastnine.parknic.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.eastnine.domain.dto.ParkingDto
import com.eastnine.parknic.R
import com.eastnine.parknic.databinding.ActivityMainBinding
import com.eastnine.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupListener()
        setupParkingData()
    }

    private fun setupListener() {
        binding.mainMapView.setMapViewEventListener(object: MapView.MapViewEventListener {
            override fun onMapViewInitialized(mapView: MapView?) {
                mapView?.setDefaultCurrentLocationMarker()
                mapView?.setShowCurrentLocationMarker(true)
            }

            override fun onMapViewCenterPointMoved(mapView: MapView?, p1: MapPoint?) = Unit

            override fun onMapViewZoomLevelChanged(mapView: MapView?, p1: Int) = Unit

            override fun onMapViewSingleTapped(mapView: MapView?, p1: MapPoint?) = Unit

            override fun onMapViewDoubleTapped(mapView: MapView?, p1: MapPoint?) = Unit

            override fun onMapViewLongPressed(mapView: MapView?, p1: MapPoint?) = Unit

            override fun onMapViewDragStarted(mapView: MapView?, p1: MapPoint?) = Unit

            override fun onMapViewDragEnded(mapView: MapView?, p1: MapPoint?) = Unit

            override fun onMapViewMoveFinished(mapView: MapView?, p1: MapPoint?) = Unit
        })
        binding.mainMapView.setCurrentLocationEventListener(object: MapView.CurrentLocationEventListener {
            override fun onCurrentLocationUpdate(mapView: MapView?, currentLocation: MapPoint?, accuracyInMeters: Float) {
            }

            override fun onCurrentLocationDeviceHeadingUpdate(mapView: MapView?, accuracyInMeters: Float) = Unit

            override fun onCurrentLocationUpdateFailed(mapView: MapView?) = Unit

            override fun onCurrentLocationUpdateCancelled(mapView: MapView?) = Unit
        })

        viewModel.setOnSplashDataListener(object: OnMainDataListener {
            override fun getParking(parkingList: List<ParkingDto>) {
                for (parking in parkingList) {
                    val marker = MapPOIItem()
                    marker.run {
                        itemName = parking.parkingName
                        mapPoint = MapPoint.mapPointWithGeoCoord(parking.lat, parking.lng)
                        markerType = MapPOIItem.MarkerType.RedPin
                    }
                    binding.mainMapView.addPOIItem(marker)
                }
            }
        })
    }

    private fun setupParkingData() {
        viewModel.getParking()
    }

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}