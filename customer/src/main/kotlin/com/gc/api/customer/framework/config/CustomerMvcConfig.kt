package com.gc.api.customer.framework.config

import com.gc.common.framework.logging.ServerLoggingInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CustomerMvcConfig(
  private val serverLoggingInterceptor: ServerLoggingInterceptor
): WebMvcConfigurer {

  override fun addCorsMappings(registry: CorsRegistry) {
    registry
      .addMapping("/**")
      .allowedHeaders("*")
      .allowedMethods("*")
      .maxAge(3600)
  }

  override fun addInterceptors(registry: InterceptorRegistry) {
    registry.addInterceptor(serverLoggingInterceptor)
  }
}