package com.gc.common.exception

import org.springframework.http.HttpStatus

class CustomForbiddenException(message: String)
  : CustomException(
  httpStatusCode = HttpStatus.FORBIDDEN.value(),
  code = "COMMON403",
  message) {
}