package com.gc.api.customer.adapter.out.persistence.member

import com.gc.api.customer.adapter.out.external.social_login.dto.OAuthProfile
import com.gc.api.customer.application.port.out.persistence.member.PostMemberPort
import com.gc.api.customer.domain.model.member.Member
import com.gc.storage.document.member.MemberMongoRepository
import org.springframework.stereotype.Repository

@Repository
class PostMemberRepository(
    val memberMongoRepository: MemberMongoRepository,
) : PostMemberPort {

    override fun save(oauthProfile: OAuthProfile): Member {
        val newMember = MemberMapper.from(oauthProfile)
        return Member.from(memberMongoRepository.save(newMember))
    }
}