package com.gc.api.customer.adapter.`in`.rest.group

import com.gc.api.customer.adapter.`in`.dto.request.group.CreateGroupRequest
import com.gc.api.customer.adapter.`in`.dto.response.ResponseData
import com.gc.api.customer.adapter.`in`.dto.response.group.GroupResponse
import com.gc.api.customer.application.service.facade.group.GroupFacade
import com.gc.api.customer.framework.annotation.RequestInfo
import com.gc.api.customer.framework.annotation.RequireAuth
import com.gc.api.customer.utils.UrlConstant
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(UrlConstant.GROUP)
class GroupController(
    private val requestInfo: RequestInfo,
    private val groupFacade: GroupFacade,
) {

    @PostMapping
    @RequireAuth
    fun createGroup(@RequestBody request: CreateGroupRequest): ResponseData<GroupResponse> {
        val newGroup = groupFacade.createGroup(request.toServiceRequest(requestInfo.member))
        return ResponseData.success(GroupResponse.toGroupResponse(newGroup))
    }
}