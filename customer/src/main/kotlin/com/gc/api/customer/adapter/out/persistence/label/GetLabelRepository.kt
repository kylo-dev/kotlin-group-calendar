package com.gc.api.customer.adapter.out.persistence.label

import com.gc.api.customer.application.port.out.persistence.label.GetMemberLabelPort
import com.gc.api.customer.domain.model.label.EventLabel
import com.gc.storage.document.label.DefaultLabelDocument
import com.gc.storage.document.label.DefaultLabelMongoRepository
import com.gc.storage.document.label.MemberLabelDocument
import com.gc.storage.document.label.MemberLabelMongoRepository
import org.springframework.stereotype.Repository

@Repository
class GetLabelRepository(
  private val defaultLabelMongoRepository: DefaultLabelMongoRepository,
  private val memberLabelMongoRepository: MemberLabelMongoRepository,
): GetMemberLabelPort {

  override fun getDefaultLabels(): List<EventLabel> {
    val results = defaultLabelMongoRepository.findAll()
    return results.stream()
      .map { fromDefaultLabel(it) }
      .toList()
  }

  override fun getCustomLabels(memberId: String): List<EventLabel> {
    val results = memberLabelMongoRepository.findAllByMemberId(memberId)
    return results.stream()
      .map { fromCustomLabel(it) }
      .toList()
  }


  private fun fromDefaultLabel(defaultLabelDocument: DefaultLabelDocument): EventLabel {
    return EventLabel(defaultLabelDocument.id!!, defaultLabelDocument.label, defaultLabelDocument.color)
  }

  private fun fromCustomLabel(memberLabelDocument: MemberLabelDocument): EventLabel {
    return EventLabel(memberLabelDocument.labelId, memberLabelDocument.label, memberLabelDocument.color)
  }
}