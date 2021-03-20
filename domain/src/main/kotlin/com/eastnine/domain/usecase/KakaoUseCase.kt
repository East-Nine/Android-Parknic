package com.eastnine.domain.usecase

import com.eastnine.domain.dto.AddressDto
import com.eastnine.domain.dto.ParkingDto
import com.eastnine.domain.repository.KakaoRepository
import com.eastnine.domain.repository.ParkingRepository
import io.reactivex.rxjava3.core.Single

class KakaoUseCase(private val kakaoRepository: KakaoRepository) {
    fun getAddress(): Single<AddressDto> = kakaoRepository.getAddress()
}