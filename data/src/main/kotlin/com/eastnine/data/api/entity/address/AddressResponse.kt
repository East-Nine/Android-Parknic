package com.eastnine.data.api.entity.address


import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("meta")
    val meta: Meta
)