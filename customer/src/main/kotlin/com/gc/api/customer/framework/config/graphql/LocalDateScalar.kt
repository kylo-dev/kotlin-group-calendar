//package com.gc.api.customer.framework.config.graphql
//
//import graphql.language.StringValue
//import graphql.schema.Coercing
//import graphql.schema.CoercingParseValueException
//import graphql.schema.CoercingSerializeException
//import java.time.LocalDate
//import java.time.format.DateTimeFormatter
//
//object LocalDateScalar : Coercing<LocalDate, String> {
//    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//
//    override fun serialize(input: Any): String {
//        return (input as? LocalDate)?.format(formatter)
//            ?: throw CoercingSerializeException("Invalid LocalDate value: $input")
//    }
//
//    override fun parseValue(input: Any): LocalDate {
//        return try {
//            println(input)
//            LocalDate.parse(input.toString(), formatter)
//        } catch (e: Exception) {
//            throw CoercingParseValueException("Invalid LocalDate format: $input")
//        }
//    }
//
//    override fun parseLiteral(input: Any): LocalDate {
//        if (input is StringValue) {
//            return parseValue(input.value)
//        }
//        throw CoercingParseValueException("Expected AST type 'StringValue' but got: ${input::class.simpleName}")
//    }
//}