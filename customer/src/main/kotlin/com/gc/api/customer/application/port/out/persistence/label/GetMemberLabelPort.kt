package com.gc.api.customer.application.port.out.persistence.label

import com.gc.api.customer.domain.model.label.EventLabel
import com.gc.api.customer.domain.model.member.Member

interface GetMemberLabelPort {

  fun getDefaultLabels(): List<EventLabel>
  fun getCustomLabels(member: Member): List<EventLabel>
  fun getLabel(memberId: String, labelId: String): EventLabel
}