package com.eastnine.data.api.entity.address


import com.google.gson.annotations.SerializedName

data class Document(
    @SerializedName("road_address")
    val roadAddress: RoadAddress,
    @SerializedName("address")
    val address: Address
)