package com.eastnine.data.repository

import com.eastnine.data.api.service.ParkingService
import com.eastnine.data.api.adapter.ParkingAdapter
import com.eastnine.domain.dto.ParkingDto
import com.eastnine.domain.repository.ParkingRepository
import io.reactivex.rxjava3.core.Single

class ParkingRepositoryImpl(private val parkingService: ParkingService): ParkingRepository {
    override fun getParkInfo(town: String): Single<List<ParkingDto>> =
        parkingService.getParking(town).map {
            it.getParkInfo.row.map(ParkingAdapter::entityToDto)
        }
}