package com.eastnine.parknic.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eastnine.domain.usecase.KakaoUseCase
import com.eastnine.domain.usecase.ParkingUseCase
import com.eastnine.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import net.daum.mf.map.api.MapPoint
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val kakaoUseCase: KakaoUseCase,
    private val parkingUseCase: ParkingUseCase
): BaseViewModel() {
    private var onMainDataListener: OnMainDataListener? = null
    var mapCenterPoint: MapPoint? = null

    private val _searchLocationButtonVisibility = MutableLiveData(View.GONE)
    val searchLocationButtonVisibility: LiveData<Int> get() = _searchLocationButtonVisibility

    fun setSearchLocationButtonVisibility(visibility: Boolean) {
        _searchLocationButtonVisibility.value = if (visibility) View.VISIBLE else View.GONE
    }

    fun getParking(apiKey: String, lat: Double, lng: Double) {
        kakaoUseCase.getAddress(lat, lng)
            .subscribeOn(AndroidSchedulers.mainThread())
            .concatMap {
                parkingUseCase.getParking(apiKey, it.region3depthName)
                    .subscribeOn(AndroidSchedulers.mainThread())
            }
            .subscribe(
                {
                    onMainDataListener?.getParking(it)
                },
                {

                }
            ).also(::addDisposable)
    }

    fun onClickSearchCurrentLocation(seoulApiKey: String) {
        mapCenterPoint?.let {
            getParking(seoulApiKey, it.mapPointGeoCoord.latitude, it.mapPointGeoCoord.longitude)
        }
    }

    fun setOnSplashDataListener(listener: OnMainDataListener) {
        onMainDataListener = listener
    }
}