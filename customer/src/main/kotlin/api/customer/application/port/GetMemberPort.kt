package api.customer.application.port

interface GetMemberPort {

  fun getMemberByEmail(email: String)
}