package com.gc.api.customer.adapter.`in`.rest.member

import com.gc.api.customer.adapter.`in`.dto.request.member.ChangeMemberRequest
import com.gc.api.customer.adapter.`in`.dto.response.ResponseData
import com.gc.api.customer.domain.service.member.MemberCommandService
import com.gc.api.customer.utils.UrlConstant
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(UrlConstant.MEMBER)
class MemberController(
    private val memberCommandService: MemberCommandService,
) {

    @PatchMapping("/{memberId}")
    fun changeProfile(
        @PathVariable memberId: String,
        @RequestBody changeMemberRequest: ChangeMemberRequest,): ResponseData<String> {

        memberCommandService.changeProfile(changeMemberRequest.toServiceRequest(memberId))
        return ResponseData.success(memberId)
    }
}