package com.gc.api.customer.adapter.`in`.rest.group

import com.gc.api.customer.adapter.`in`.dto.request.group.member.UpdateGroupMemberRequest
import com.gc.api.customer.adapter.`in`.dto.response.ResponseData
import com.gc.api.customer.domain.service.group.member.GroupMemberCommandService
import com.gc.api.customer.framework.annotation.RequestInfo
import com.gc.api.customer.utils.UrlConstant
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(UrlConstant.GROUP)
class GroupMemberController(
    private val groupMemberCommandService: GroupMemberCommandService,
    private val requestInfo: RequestInfo,
) {

    @PatchMapping("/{groupId}/members")
    fun updateGroupMember(
        @PathVariable groupId: String,
        @RequestBody request: UpdateGroupMemberRequest
    ): ResponseData<String> {
        groupMemberCommandService.updateGroupMember(
            request.toServiceRequest(groupId, requestInfo.member)
        )
        return ResponseData.success(requestInfo.member.id)
    }

}