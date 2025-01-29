package com.gc.api.customer.application.port.out

interface GetMemberPort {

  fun getMemberByEmail(email: String)
}