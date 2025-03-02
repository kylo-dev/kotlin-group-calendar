package com.gc.api.customer.framework.config.feign

import com.gc.utils.logger
import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class FeignRequestInterceptor : RequestInterceptor {

  override fun apply(template: RequestTemplate?) {

    val logMessage = """
      [Feign Request]
          Method: ${template?.method()}
          Endpoint: ${template?.url()}
          Headers: ${template?.headers()}
          Payload: ${template?.let { getRequestBody(it) }}
    """.trimEnd()

    logger.info{ logMessage }
  }

  private fun getRequestBody(template: RequestTemplate): String {
    return template.body()?.let{
      String(it, StandardCharsets.UTF_8)
    } ?: "N/A"
  }
}