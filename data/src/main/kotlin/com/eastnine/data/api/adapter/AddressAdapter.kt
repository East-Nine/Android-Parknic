package com.eastnine.data.api.adapter

import com.eastnine.data.api.entity.address.Address
import com.eastnine.domain.dto.AddressDto

object AddressAdapter {
    fun entityToDto(address: Address): AddressDto =
        AddressDto(
            address = address.addressName,
            region1depthName = address.region1depthName,
            region2depthName = address.region2depthName,
            region3depthName = address.region3depthName
        )
}