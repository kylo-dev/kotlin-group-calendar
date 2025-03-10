package com.gc.api.customer.adapter.`in`.graphql.label

import com.gc.api.customer.domain.model.label.EventLabel
import com.gc.api.customer.domain.service.label.LabelQueryService
import com.gc.api.customer.framework.annotation.RequestInfo
import com.gc.api.customer.framework.annotation.RequireAuth
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class LabelGraphController(
  private val labelQueryService: LabelQueryService,
  private val requestInfo: RequestInfo,
) {

  @QueryMapping
  @RequireAuth
  fun labels(): List<EventLabel> {
    return labelQueryService.getLabels(requestInfo.member.id)
  }

}