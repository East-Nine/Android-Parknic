package com.eastnine.domain.usecase

import com.eastnine.domain.dto.ParkingDto
import com.eastnine.domain.repository.ParkingRepository
import io.reactivex.rxjava3.core.Single

class ParkingUseCase(private val parkingRepository: ParkingRepository) {
    fun getParking(apiKey: String, town: String): Single<List<ParkingDto>> = parkingRepository.getParkInfo(apiKey, town)
}