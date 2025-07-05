package com.gc.api.customer.adapter.`in`.rest.label

import com.gc.api.customer.adapter.`in`.dto.request.label.ChangeLabelRequest
import com.gc.api.customer.adapter.`in`.dto.response.ResponseData
import com.gc.api.customer.domain.model.label.EventLabel
import com.gc.api.customer.domain.service.label.LabelCommandService
import com.gc.api.customer.domain.service.label.LabelQueryService
import com.gc.api.customer.framework.annotation.RequestInfo
import com.gc.api.customer.framework.annotation.RequireAuth
import com.gc.api.customer.utils.UrlConstant
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(UrlConstant.LABEL)
class LabelController(
    private val labelQueryService: LabelQueryService,
    private val labelCommandService: LabelCommandService,
    private val requestInfo: RequestInfo,
) {

    @GetMapping
    @RequireAuth
    fun getLabels(): ResponseData<List<EventLabel>> {
        return ResponseData.success(labelQueryService.getLabels(requestInfo.member))
    }

    @PatchMapping("/{labelId}")
    fun changeLabel(
        @PathVariable labelId: String,
        @RequestBody changeLabelRequest: ChangeLabelRequest,
    ): ResponseData<String> {
        val changeMemberLabelDto =
            changeLabelRequest.toServiceRequest(changeLabelRequest, labelId, requestInfo.member)
        labelCommandService.changeMemberLabel(changeMemberLabelDto)
        return ResponseData.success(labelId)
    }
}