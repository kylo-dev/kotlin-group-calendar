package com.gc.api.customer.framework.config.feign

import common.exception.CustomAuthenticationException
import common.exception.CustomNotFoundException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean

class FeignClientConfig {

  @Bean
  fun errorDecoder(): ErrorDecoder {
    return FeignErrorDecoder()
  }

  private class FeignErrorDecoder: ErrorDecoder {
    override fun decode(method: String, response: Response): Exception {

      return when (response.status()) {
        401 -> CustomAuthenticationException("인증되지 않은 토큰입니다.")
        404 -> CustomNotFoundException("사용자 정보를 찾을 수 없습니다.")
        else -> FeignException.errorStatus(method, response)
      }
    }
  }

}