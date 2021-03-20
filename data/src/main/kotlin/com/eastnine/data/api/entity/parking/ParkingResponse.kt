package com.eastnine.data.api.entity.parking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ParkingResponse(
    @SerializedName("GetParkInfo") @Expose val getParkInfo: GetParkInfo
)