package com.eastnine.parknic.ui.main

import com.eastnine.domain.dto.ParkingDto

interface OnMainDataListener {
    fun getParking(parkingList: List<ParkingDto>)
}