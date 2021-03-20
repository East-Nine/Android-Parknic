package com.eastnine.data.api.entity.parking

data class GetParkInfo(
    val RESULT: RESULT,
    val list_total_count: Int,
    val row: List<Row>
)