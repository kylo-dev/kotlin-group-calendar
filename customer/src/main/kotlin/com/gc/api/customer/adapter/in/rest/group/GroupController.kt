package com.gc.api.customer.adapter.`in`.rest.group

import com.gc.api.customer.adapter.`in`.dto.request.group.CreateGroupRequest
import com.gc.api.customer.adapter.`in`.dto.request.group.UpdateGroupRequest
import com.gc.api.customer.adapter.`in`.dto.response.ResponseData
import com.gc.api.customer.adapter.`in`.dto.response.group.GroupResponse
import com.gc.api.customer.application.service.facade.group.GroupFacade
import com.gc.api.customer.domain.service.group.GroupCommandService
import com.gc.api.customer.framework.annotation.RequestInfo
import com.gc.api.customer.framework.annotation.RequireAuth
import com.gc.api.customer.utils.UrlConstant
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(UrlConstant.GROUP)
class GroupController(
    private val requestInfo: RequestInfo,
    private val groupFacade: GroupFacade,
    private val groupCommandService: GroupCommandService,
) {

    @PostMapping
    @RequireAuth
    fun createGroup(@RequestBody request: CreateGroupRequest): ResponseData<GroupResponse> {
        val newGroup = groupFacade.createGroup(request.toServiceRequest(requestInfo.member))
        return ResponseData.success(GroupResponse.toGroupResponse(newGroup))
    }

    @PatchMapping("/{groupId}")
    fun updateGroupInfo(@PathVariable groupId: String,
                        @RequestBody request: UpdateGroupRequest): ResponseData<String> {
        groupCommandService.updateGroup(request.toServiceRequest(groupId))
        return ResponseData.success(groupId)
    }
}