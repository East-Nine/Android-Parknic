package com.eastnine.domain.repository

import com.eastnine.domain.dto.ParkingDto
import io.reactivex.rxjava3.core.Single

interface ParkingRepository {
    fun getParkInfo(): Single<List<ParkingDto>>
}