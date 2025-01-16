package common.exception

import org.springframework.http.HttpStatus

class CustomAuthenticationException(message: String)
  : CustomException(
    httpStatusCode = HttpStatus.UNAUTHORIZED.value(),
    code = "COMMON401",
    message,
  ) {
}