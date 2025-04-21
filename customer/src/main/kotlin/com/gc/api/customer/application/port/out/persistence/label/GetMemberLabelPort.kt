package com.gc.api.customer.application.port.out.persistence.label

import com.gc.api.customer.domain.model.label.EventLabel

interface GetMemberLabelPort {

  fun getDefaultLabels(): List<EventLabel>
  fun getCustomLabels(memberId: String): List<EventLabel>
  fun getLabel(memberId: String, labelId: String): EventLabel
}