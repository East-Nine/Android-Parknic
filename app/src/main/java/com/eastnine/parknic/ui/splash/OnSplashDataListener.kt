package com.eastnine.parknic.ui.splash

import com.eastnine.domain.dto.ParkingDto

interface OnSplashDataListener {
    fun getParking(parkingList: List<ParkingDto>)
}