package com.gc.api.customer.adapter.`in`.rest

import com.gc.common.adapter.out.persistence.document.MemberDocument
import com.gc.common.adapter.out.persistence.jpa.MemberJpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
  private val memberJpaRepository: MemberJpaRepository,
) {

  @PostMapping
  fun saveMember(): String {
    val newMember = MemberDocument(
      userName = "kylo",
      email = "kylo@kakao.com",
      isActive = true,
      isAdmin = true,
      profile = "kakao",
      oauthProvider = "kakao")
    val saveMember = memberJpaRepository.save(newMember)
    return saveMember.id!!
  }

  @GetMapping
  fun getMembers(): List<MemberDocument> {
    val results = memberJpaRepository.findAll()
    return results
  }
}