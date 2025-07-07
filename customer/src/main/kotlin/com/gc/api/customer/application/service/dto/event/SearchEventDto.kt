package com.gc.api.customer.application.service.dto.event

import com.gc.api.customer.domain.model.member.Member

data class SearchEventDto(
    val memberId: String,
    val keyword: String,
    val cursor: String?,
    val size: Int,
) {
    companion object {
        fun of(member: Member, q: String, cursor: String?, size: Int): SearchEventDto {
            return SearchEventDto(
                memberId = member.id,
                keyword = q,
                cursor = cursor,
                size = size,
            )
        }
    }
}
