package com.gc.api.customer.framework.config.graphql

import graphql.schema.GraphQLScalarType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer


@Configuration
class GraphQLConfig {
  @Bean
  fun localDateTimeScalar(): RuntimeWiringConfigurer {
    return RuntimeWiringConfigurer { wiringBuilder ->
      wiringBuilder.scalar(
        GraphQLScalarType.newScalar()
        .name("LocalDateTime")
        .coercing(LocalDateTimeScalar)
        .build()
      )
    }
  }

  @Bean
  fun localDateScalar(): RuntimeWiringConfigurer {
    return RuntimeWiringConfigurer { wiringBuilder ->
      wiringBuilder.scalar(
        GraphQLScalarType.newScalar()
          .name("LocalDate")
          .coercing(LocalDateScalar)
          .build()
      )
    }
  }

}