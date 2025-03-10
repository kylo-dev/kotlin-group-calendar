package com.gc.api.customer.domain.service.label

import com.gc.api.customer.application.port.out.persistence.label.GetMemberLabelPort
import com.gc.api.customer.domain.model.label.EventLabel
import org.springframework.stereotype.Service

@Service
class LabelQueryService(
  private val getMemberLabelPort: GetMemberLabelPort,
) {

  fun getLabels(memberId: String): List<EventLabel> {
    // default label
    val defaultLabels: List<EventLabel> = getMemberLabelPort.getDefaultLabels()

    // custom label
    val customLabels: List<EventLabel> = getMemberLabelPort.getCustomLabels(memberId)

    val labelMap = defaultLabels.associateBy { it.id }.toMutableMap()

    customLabels.forEach { customLabel ->
      labelMap[customLabel.id] = customLabel
    }

    return labelMap.values.toList()

  }
}