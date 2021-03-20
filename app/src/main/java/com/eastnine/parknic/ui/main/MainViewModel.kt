package com.eastnine.parknic.ui.main

import com.eastnine.domain.usecase.KakaoUseCase
import com.eastnine.domain.usecase.ParkingUseCase
import com.eastnine.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val kakaoUseCase: KakaoUseCase,
    private val parkingUseCase: ParkingUseCase
): BaseViewModel() {
    private var onMainDataListener: OnMainDataListener? = null

    fun getParking() {
        kakaoUseCase.getAddress()
            .subscribeOn(AndroidSchedulers.mainThread())
            .concatMap {
                parkingUseCase.getParking(it.region3depthName)
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

    fun setOnSplashDataListener(listener: OnMainDataListener) {
        onMainDataListener = listener
    }
}