package com.gc.common.framework.logging

import com.fasterxml.jackson.databind.ObjectMapper
import com.gc.utils.logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.util.ContentCachingResponseWrapper
import java.nio.charset.Charset

@Component
class ServerLoggingInterceptor(
  private val objectMapper: ObjectMapper
) : HandlerInterceptor {

  override fun preHandle(
    request: HttpServletRequest,
    response: HttpServletResponse,
    handler: Any
  ): Boolean {

    val headers = mutableMapOf<String, String>()
    for (header in request.headerNames) {
      headers.put(header, request.getHeader(header))
    }

    val requestBytes = getHttpRequestBytes(request)
    val requestObject = getHttpRequestObject(requestBytes, handler)

    val logMessage = """
      [API START]
          Endpoint: ${getFullRequestUrl(request)}
          Payload: ${getLogBodyContent(request.contentType, requestObject, requestBytes)}
          Headers: ${objectMapper.writeValueAsString(headers)}
    """.trimEnd()
    logger.info { logMessage }
    return true
  }

  override fun afterCompletion(
    request: HttpServletRequest,
    response: HttpServletResponse,
    handler: Any,
    ex: Exception?
  ) {
    val requestBytes = getHttpRequestBytes(request)
    val requestObject = getHttpRequestObject(requestBytes, handler)

    val responseBytes = getHttpResponseBytes(response)
    val responseObject = getHttpResponseObject(responseBytes, handler)

    val logMessage = """
      [Request]
          ip_from: ${getRemoteAddress(request)}
          method: ${request.method}
          endpoint: ${getFullRequestUrl(request)}
          payload: ${getLogBodyContent(request.contentType, requestObject, requestBytes)}
          
      [Response]
          status: ${response.status}
          response_data: ${getLogBodyContent(response.contentType, responseObject, responseBytes)}
    """.trimEnd()

    logger.info { logMessage }
  }

  // HttpRequest
  fun getHttpRequestBytes(
    request: HttpServletRequest
  ): ByteArray? =
    (request as? CachingBodyHttpServletRequest)?.inputStream?.readBytes()

  fun getHttpRequestObject(requestBytes: ByteArray?, handler: Any): Any? {
    return try {
      val handlerMethod = handler as? HandlerMethod ?: return null
      for (methodParameter in handlerMethod.methodParameters) {
        if (methodParameter.hasParameterAnnotation(RequestBody::class.java)) {
          return objectMapper.readValue(requestBytes, Map::class.java)
        }
      }
    } catch (e: Exception) {
      null
    }
  }

  // HttpResponse
  fun getHttpResponseBytes(response: HttpServletResponse): ByteArray? =
    (response as? ContentCachingResponseWrapper)?.contentAsByteArray

  fun getHttpResponseObject(responseBytes: ByteArray?, handler: Any): Any? {
    return try {
      val handlerMethod = handler as? HandlerMethod ?: return null
      return objectMapper.readValue(responseBytes, handlerMethod.returnType.parameterType)
    } catch (_: Exception) {
      null
    }
  }

  fun getFullRequestUrl(request: HttpServletRequest): String {
    val queryString = request.queryString?.let { "?${it}" } ?: ""
    return request.requestURI + queryString
  }

  fun getLogBodyContent(contentType: String?, any: Any?, bytes: ByteArray?): String {
    return try {
      when {
        isJsonContentType(contentType) -> any?.let {
          objectMapper.writeValueAsString(it) } ?: bytesToString(bytes)
        else -> bytesToString(bytes)
      }
    } catch (_: Exception) {
      bytesToString(bytes)
    }
  }

  fun isJsonContentType(contentType: String?): Boolean {
    return contentType?.lowercase()?.contains("application/json") ?: false
  }

  fun bytesToString(bytes: ByteArray?): String {
    return if (bytes?.isNotEmpty() == true) String(bytes, Charset.defaultCharset()) else "N/A"
  }

  fun getRemoteAddress(request: HttpServletRequest): String =
    request.getHeader("X-Forwarded-For")?:request.remoteAddr

}