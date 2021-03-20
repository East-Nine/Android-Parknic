package com.eastnine.domain.repository

import com.eastnine.domain.dto.AddressDto
import com.eastnine.domain.dto.ParkingDto
import io.reactivex.rxjava3.core.Single

interface KakaoRepository {
    fun getAddress(): Single<AddressDto>
}