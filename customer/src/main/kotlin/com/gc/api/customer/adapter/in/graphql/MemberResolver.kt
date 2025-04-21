package com.gc.api.customer.adapter.`in`.graphql

import com.gc.api.customer.domain.model.member.Member
import com.gc.storage.document.member.MemberMongoRepository
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class MemberResolver(
  val memberMongoRepository: MemberMongoRepository,
) {

  @QueryMapping
  fun members(): List<Member> {
    val results = memberMongoRepository.findAll()
    return results.stream()
      .map { Member.from(it) }
      .toList()
  }
}