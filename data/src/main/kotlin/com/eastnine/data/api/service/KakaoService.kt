package com.eastnine.data.api.service

import com.eastnine.data.api.entity.address.AddressResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoService {
    
    @GET("v2/local/geo/coord2address.json?input_coord=WGS84")
    fun getAddress(@Query("x") x: Double, @Query("y") y: Double): Single<AddressResponse>
}