package com.gc.api.customer.application.service.dto.event

import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

data class GetCalendarDto(
    val memberId: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
) {

    companion object {
        fun of(memberId: String, startDate: LocalDate, endDate: LocalDate?): GetCalendarDto {

            val endDate = endDate ?: startDate.with(TemporalAdjusters.lastDayOfMonth())

            return GetCalendarDto(
                memberId,
                startDate,
                endDate,
            )
        }
    }
}
