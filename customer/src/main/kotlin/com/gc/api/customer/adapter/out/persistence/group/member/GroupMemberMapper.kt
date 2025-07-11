//package com.gc.api.customer.adapter.out.persistence.group.member
//
//import com.gc.api.customer.domain.model.group.GroupMember
//import com.gc.storage.document.group.GroupMemberDocument
//
//object GroupMemberMapper {
//    fun from(document: GroupMemberDocument): GroupMember {
//        return GroupMember(
//            groupId = document.groupId,
//            memberId = document.memberId,
//            memberNickname = document.nickname,
//            memberProfile = document.profile,
//        )
//    }
//}