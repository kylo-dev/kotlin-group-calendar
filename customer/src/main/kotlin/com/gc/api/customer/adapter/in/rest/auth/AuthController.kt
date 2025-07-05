package com.gc.api.customer.adapter.`in`.rest.auth

import com.gc.api.customer.adapter.`in`.dto.response.ResponseData
import com.gc.api.customer.application.service.facade.MemberFacade
import com.gc.api.customer.domain.service.auth.AuthService
import com.gc.api.customer.framework.security.TokenDto
import com.gc.api.customer.utils.UrlConstant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(UrlConstant.LOGIN)
class AuthController(
    val authService: AuthService,
    val memberFacade: MemberFacade,
) {

    @GetMapping("/kakao")
    fun kakaoLogin(@RequestParam("code") accessCode: String): ResponseData<TokenDto> {
        val kakaoProfile = authService.kakaoLogin(accessCode)
        return ResponseData.success(memberFacade.loginOrJoin(kakaoProfile))
    }

    @GetMapping("/naver")
    fun naverLogin(@RequestParam("code") accessCode: String): ResponseData<TokenDto> {
        val naverProfile = authService.naverLogin(accessCode)
        return ResponseData.success(memberFacade.loginOrJoin(naverProfile))
    }
}