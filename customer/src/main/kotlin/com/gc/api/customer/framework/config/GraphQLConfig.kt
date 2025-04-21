package com.gc.api.customer.framework.config

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

//  @Bean
//  fun dateScalar(): RuntimeWiringConfigurer {
//    return RuntimeWiringConfigurer { wiringBuilder: RuntimeWiring.Builder ->
//      wiringBuilder.scalar(
//        ExtendedScalars.Date
//      )
//    }
//  }
//
//  @Bean
//  fun dateTimeScalar(): RuntimeWiringConfigurer {
//    return RuntimeWiringConfigurer { wiringBuilder: RuntimeWiring.Builder ->
//      wiringBuilder.scalar(
//        ExtendedScalars.DateTime
//      )
//    }
//  }

//  @Bean
//  fun timeScalar(): RuntimeWiringConfigurer {
//    return RuntimeWiringConfigurer { wiringBuilder: RuntimeWiring.Builder ->
//      wiringBuilder.scalar(
//        ExtendedScalars.Time
//      )
//    }
//  }

}