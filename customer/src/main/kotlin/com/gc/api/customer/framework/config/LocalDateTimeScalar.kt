package com.gc.api.customer.framework.config

import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LocalDateTimeScalar : Coercing<LocalDateTime, String> {
  private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")

  override fun serialize(input: Any): String {
    return (input as? LocalDateTime)?.format(formatter)
      ?: throw CoercingSerializeException("Invalid LocalDateTime value: $input")
  }

  override fun parseValue(input: Any): LocalDateTime {
    return try {
      println(input)
      LocalDateTime.parse(input.toString(), formatter)
    } catch (e: Exception) {
      throw CoercingParseValueException("Invalid LocalDateTime format: $input")
    }
  }

  override fun parseLiteral(input: Any): LocalDateTime {
    if (input is StringValue) {
      return parseValue(input.value)
    }
    throw CoercingParseValueException("Expected AST type 'StringValue' but got: ${input::class.simpleName}")
  }
}