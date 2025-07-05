package com.gc.api.customer.application.service.dto.event

import com.gc.api.customer.domain.model.member.Member
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

data class GetCalendarDto(
    val memberId: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
) {

    companion object {
        fun of(member: Member, startDate: LocalDate, endDate: LocalDate?): GetCalendarDto {

            val endDate = endDate ?: startDate.with(TemporalAdjusters.lastDayOfMonth())

            return GetCalendarDto(
                member.id,
                startDate,
                endDate,
            )
        }
    }
}
