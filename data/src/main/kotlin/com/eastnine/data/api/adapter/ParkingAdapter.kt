package com.eastnine.data.api.adapter

import com.eastnine.data.api.entity.Parking
import com.eastnine.domain.dto.ParkingDto

object ParkingAdapter {
    fun entityToDto(parking: Parking): ParkingDto =
        ParkingDto(
            address = parking.address,
            addRate = parking.addRate,
            addTimeRate = parking.addTimeRate,
            assignCode = parking.assignCode,
            assignCodeName = parking.assignCodeName,
            busAddRate = parking.busAddRate,
            busAddTimeRate = parking.busAddTimeRate,
            busRate = parking.busRate,
            busTimeRate = parking.busTimeRate,
            capacity = parking.capacity,
            datMaximum = parking.datMaximum,
            fullTimeMonthly = parking.fullTimeMonthly,
            grpParkName = parking.grpParkName,
            holidayBeginTime = parking.holidayBeginTime,
            holidayEndTime = parking.holidayEndTime,
            holidayPayName = parking.holidayPayName,
            holidayPayYN = parking.holidayPayYN,
            lat = parking.lat,
            lng = parking.lng,
            nightFreeOpen = parking.nightFreeOpen,
            nightFreeOpenName = parking.nightFreeOpenName,
            operationRule = parking.operationRule,
            operationRuleName = parking.operationRuleName,
            parkingCode = parking.parkingCode,
            parkingName = parking.parkingName,
            parkingType = parking.parkingType,
            parkingTypeName = parking.parkingTypeName,
            payName = parking.payName,
            payYN = parking.payYN,
            rate = parking.rate,
            saturdayPayName = parking.saturdayPayName,
            saturdayPayYN = parking.saturdayPayYN,
            syncTime = parking.syncTime,
            tel = parking.tel,
            timeRate = parking.timeRate,
            weekdayBeginTime = parking.weekdayBeginTime,
            weekdayEndTime = parking.weekdayEndTime,
            weekendBeginTime = parking.weekendBeginTime,
            weekendEndTime = parking.weekendEndTime
        )

    fun dtotoEntity(parkingDto: ParkingDto): Parking =
        Parking(
            address = parkingDto.address,
            addRate = parkingDto.addRate,
            addTimeRate = parkingDto.addTimeRate,
            assignCode = parkingDto.assignCode,
            assignCodeName = parkingDto.assignCodeName,
            busAddRate = parkingDto.busAddRate,
            busAddTimeRate = parkingDto.busAddTimeRate,
            busRate = parkingDto.busRate,
            busTimeRate = parkingDto.busTimeRate,
            capacity = parkingDto.capacity,
            datMaximum = parkingDto.datMaximum,
            fullTimeMonthly = parkingDto.fullTimeMonthly,
            grpParkName = parkingDto.grpParkName,
            holidayBeginTime = parkingDto.holidayBeginTime,
            holidayEndTime = parkingDto.holidayEndTime,
            holidayPayName = parkingDto.holidayPayName,
            holidayPayYN = parkingDto.holidayPayYN,
            lat = parkingDto.lat,
            lng = parkingDto.lng,
            nightFreeOpen = parkingDto.nightFreeOpen,
            nightFreeOpenName = parkingDto.nightFreeOpenName,
            operationRule = parkingDto.operationRule,
            operationRuleName = parkingDto.operationRuleName,
            parkingCode = parkingDto.parkingCode,
            parkingName = parkingDto.parkingName,
            parkingType = parkingDto.parkingType,
            parkingTypeName = parkingDto.parkingTypeName,
            payName = parkingDto.payName,
            payYN = parkingDto.payYN,
            rate = parkingDto.rate,
            saturdayPayName = parkingDto.saturdayPayName,
            saturdayPayYN = parkingDto.saturdayPayYN,
            syncTime = parkingDto.syncTime,
            tel = parkingDto.tel,
            timeRate = parkingDto.timeRate,
            weekdayBeginTime = parkingDto.weekdayBeginTime,
            weekdayEndTime = parkingDto.weekdayEndTime,
            weekendBeginTime = parkingDto.weekendBeginTime,
            weekendEndTime = parkingDto.weekendEndTime
        )
}