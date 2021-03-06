package com.eastnine.data.repository

import com.eastnine.data.api.adapter.AddressAdapter
import com.eastnine.data.api.entity.address.Address
import com.eastnine.data.api.service.KakaoService
import com.eastnine.domain.dto.AddressDto
import com.eastnine.domain.repository.KakaoRepository
import io.reactivex.rxjava3.core.Single

class KakaoRepositoryImpl(private val kakaoService: KakaoService): KakaoRepository {
    override fun getAddress(lat: Double, lng: Double): Single<AddressDto> =
        kakaoService.getAddress(lng, lat).map { response ->
            val address = response.documents.firstOrNull()?.address
            address?.let { addr ->
                AddressAdapter.entityToDto(addr)
            } ?: AddressDto()
        }
}