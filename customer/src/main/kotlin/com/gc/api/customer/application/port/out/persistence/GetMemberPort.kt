package com.gc.api.customer.application.port.out.persistence

interface GetMemberPort {

  fun getMemberByEmail(email: String)
}