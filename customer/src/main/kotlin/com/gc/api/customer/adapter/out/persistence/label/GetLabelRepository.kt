package com.gc.api.customer.adapter.out.persistence.label

import com.gc.api.customer.application.port.out.persistence.label.GetMemberLabelPort
import com.gc.api.customer.domain.model.label.EventLabel
import com.gc.api.customer.domain.model.member.Member
import com.gc.storage.document.label.DefaultLabelDocument
import com.gc.storage.document.label.DefaultLabelMongoRepository
import com.gc.storage.document.label.MemberLabelDocument
import com.gc.storage.document.label.MemberLabelMongoRepository
import common.exception.CustomNotFoundException
import org.springframework.stereotype.Repository

@Repository
class GetLabelRepository(
    private val defaultLabelMongoRepository: DefaultLabelMongoRepository,
    private val memberLabelMongoRepository: MemberLabelMongoRepository,
) : GetMemberLabelPort {

    override fun getDefaultLabels(): List<EventLabel> {
        val results = defaultLabelMongoRepository.findAll()
        return results.stream()
            .map { fromDefaultLabel(it) }
            .toList()
    }

    override fun getCustomLabels(member: Member): List<EventLabel> {
        val results = memberLabelMongoRepository.findAllByMemberId(member.id)
        return results.stream()
            .map { fromCustomLabel(it) }
            .toList()
    }

    override fun getLabel(memberId: String, labelId: String): EventLabel {
        return memberLabelMongoRepository.findByMemberIdAndLabelId(memberId, labelId)?.let {
            fromCustomLabel(it)
        }
            ?: defaultLabelMongoRepository.findById(labelId)
                .orElseThrow { CustomNotFoundException("라벨을 찾을 수 없습니다.") }
                .let { fromDefaultLabel(it) }
    }

    private fun fromDefaultLabel(defaultLabelDocument: DefaultLabelDocument): EventLabel {
        return EventLabel(
            defaultLabelDocument.id!!,
            defaultLabelDocument.label,
            defaultLabelDocument.color
        )
    }

    private fun fromCustomLabel(memberLabelDocument: MemberLabelDocument): EventLabel {
        return EventLabel(
            memberLabelDocument.labelId,
            memberLabelDocument.label,
            memberLabelDocument.color
        )
    }
}