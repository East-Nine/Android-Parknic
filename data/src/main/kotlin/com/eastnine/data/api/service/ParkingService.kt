package com.eastnine.data.api.service

import com.eastnine.data.api.entity.parking.ParkingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ParkingService {

    @GET("6e796270776e75633639535a454a78/json/GetParkInfo/0/999/{town}")
    fun getParking(@Path(value = "town", encoded = true) town: String): Single<ParkingResponse>
}