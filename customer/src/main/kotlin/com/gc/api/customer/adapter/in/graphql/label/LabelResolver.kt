package com.gc.api.customer.adapter.`in`.graphql.label

import com.gc.api.customer.adapter.`in`.dto.label.request.ChangeLabelRequest
import com.gc.api.customer.domain.model.label.EventLabel
import com.gc.api.customer.domain.service.label.LabelCommandService
import com.gc.api.customer.domain.service.label.LabelQueryService
import com.gc.api.customer.framework.annotation.RequestInfo
import com.gc.api.customer.framework.annotation.RequireAuth
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class LabelResolver(
  private val labelQueryService: LabelQueryService,
  private val labelCommandService: LabelCommandService,
  private val requestInfo: RequestInfo,
) {

  @QueryMapping
  @RequireAuth
  fun labels(): List<EventLabel> {
    return labelQueryService.getLabels(requestInfo.member.id)
  }

  @MutationMapping
  @RequireAuth
  fun changeLabel(@Argument changeLabelRequest: ChangeLabelRequest): String {
    val changeMemberLabelDto =
      changeLabelRequest.toServiceRequest(changeLabelRequest, requestInfo.member.id)
    labelCommandService.changeMemberLabel(changeMemberLabelDto)
    return changeLabelRequest.labelId
  }

}