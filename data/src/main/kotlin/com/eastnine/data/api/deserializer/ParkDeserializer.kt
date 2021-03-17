package com.eastnine.data.api.deserializer

import com.eastnine.domain.dto.ParkingDto
import com.google.gson.*
import java.lang.reflect.Type

class ParkDeserializer: JsonDeserializer<List<ParkingDto>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<ParkingDto> {
        val jsonObject = json?.asJsonObject
        val partInfo = jsonObject?.getAsJsonObject("GetParkInfo")
        val rows = partInfo?.getAsJsonArray("row")

        return rows?.map {
            Gson().fromJson<ParkingDto>(it.asJsonObject, ParkingDto::class.java)
            //rowJsonToParkDto(it.asJsonObject)
        } ?: listOf()
    }

    /*private fun rowJsonToParkDto(rowObject: JsonObject): ParkDto {
        val parkingName = rowObject.get("PARKING_NAME").asString
        val address = rowObject.get("ADDR").asString
        val parkingCode = rowObject.get("PARKING_CODE").asString
        val parkingType = rowObject.get("PARKING_TYPE").asString
        val parkingTypeName = rowObject.get("PARKING_TYPE_NM").asString
        val operationRule = rowObject.get("OPERATION_RULE").asString
        val operationRuleName = rowObject.get("OPERATION_RULE_NM").asString
        val tel = rowObject.get("TEL").asString
        val capacity = rowObject.get("CAPACITY").asInt
        val payYN = rowObject.get("PAY_YN").asString
        val payName = rowObject.get("PAY_NM").asString
        val nightFreeOpen = rowObject.get("NIGHT_FREE_OPEN").asString
        val nightFreeOpenName = rowObject.get("NIGHT_FREE_OPEN_NM").asString
        val weekdayBeginTime = rowObject.get("WEEKDAY_BEGIN_TIME").asString
        val weekdayEndTime = rowObject.get("WEEKDAY_END_TIME").asString
        val weekendBeginTime = rowObject.get("WEEKEND_BEGIN_TIME").asString
        val weekendEndTime = rowObject.get("WEEKEND_END_TIME").asString
        val holidayBeginTime = rowObject.get("HOLIDAY_BEGIN_TIME").asString
        val holidayEndTime = rowObject.get("HOLIDAY_END_TIME").asString
        val syncTime = rowObject.get("SYNC_TIME").asString
        val saturdayPayYN = rowObject.get("SATURDAY_PAY_YN").asString
        val saturdayPayName = rowObject.get("SATURDAY_PAY_NM").asString
        val holidayPayYN = rowObject.get("HOLIDAY_PAY_YN").asString
        val holidayPayName = rowObject.get("HOLIDAY_PAY_NM").asString
        val fullTimeMonthly = rowObject.get("FULLTIME_MONTHLY").asString
        val grpParkName = rowObject.get("GRP_PARKNM").asString
        val rate = rowObject.get("RATES").asInt
        val timeRate = rowObject.get("TIME_RATE").asInt
        val addRate = rowObject.get("ADD_RATES").asInt
        val addTimeRate = rowObject.get("ADD_TIME_RATE").asInt
        val busRate = rowObject.get("BUS_RATES").asInt
        val busTimeRate = rowObject.get("BUS_TIME_RATE").asInt
        val busAddTimeRate = rowObject.get("BUS_ADD_TIME_RATE").asInt
        val busAddRate = rowObject.get("BUS_ADD_RATES").asInt
        val datMaximum = rowObject.get("DAY_MAXIMUM").asInt
        val lat = rowObject.get("LAT").asDouble
        val lng = rowObject.get("LNG").asDouble
        val assignCode = rowObject.get("ASSIGN_CODE").asString
        val assignCodeName = rowObject.get("ASSIGN_CODE_NM").asString

        return ParkDto(

        )
    }*/
}