package com.eastnine.parknic.ui.splash

import com.eastnine.domain.usecase.ParkingUseCase
import com.eastnine.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val parkingUseCase: ParkingUseCase
): BaseViewModel() {
    private var onSplashDataListener: OnSplashDataListener? = null

    fun getParking() {
        parkingUseCase.getParking()
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onSplashDataListener?.getParking(it)
                },
                {

                }
            ).also(::addDisposable)
    }

    fun setOnSplashDataListener(listener: OnSplashDataListener) {
        onSplashDataListener = listener
    }
}