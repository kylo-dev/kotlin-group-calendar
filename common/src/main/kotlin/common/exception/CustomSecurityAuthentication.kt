package common.exception

import org.springframework.security.core.AuthenticationException

class CustomSecurityAuthentication(
  message: String,
  cause: Throwable
)
  : AuthenticationException(message, cause) {
}