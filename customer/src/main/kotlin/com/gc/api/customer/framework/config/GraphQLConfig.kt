package com.gc.api.customer.framework.config

import graphql.scalars.ExtendedScalars
import graphql.schema.idl.RuntimeWiring
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer


@Configuration
class GraphQLConfig {

  @Bean
  fun dateScalar(): RuntimeWiringConfigurer {
    return RuntimeWiringConfigurer { wiringBuilder: RuntimeWiring.Builder ->
      wiringBuilder.scalar(
        ExtendedScalars.Date
      )
    }
  }

  @Bean
  fun dateTimeScalar(): RuntimeWiringConfigurer {
    return RuntimeWiringConfigurer { wiringBuilder: RuntimeWiring.Builder ->
      wiringBuilder.scalar(
        ExtendedScalars.DateTime
      )
    }
  }

  @Bean
  fun timeScalar(): RuntimeWiringConfigurer {
    return RuntimeWiringConfigurer { wiringBuilder: RuntimeWiring.Builder ->
      wiringBuilder.scalar(
        ExtendedScalars.Time
      )
    }
  }

}