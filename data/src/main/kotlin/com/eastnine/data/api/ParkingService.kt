package com.eastnine.data.api

import com.eastnine.data.api.entity.Parking
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ParkingService {
    
    @GET("6e796270776e75633639535a454a78/json/GetParkInfo/0/1/")
    fun getParking(): Single<List<Parking>>
}