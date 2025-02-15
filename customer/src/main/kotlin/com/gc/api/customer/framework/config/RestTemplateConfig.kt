package com.gc.api.customer.framework.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

  @Bean
  fun restTemplate() : RestTemplate {
    val restTemplate = RestTemplate()

    val messageConverters: MutableList<HttpMessageConverter<*>> = ArrayList()
    messageConverters.add(FormHttpMessageConverter())
    messageConverters.add(MappingJackson2HttpMessageConverter())
    restTemplate.messageConverters = messageConverters

    return restTemplate
  }
}