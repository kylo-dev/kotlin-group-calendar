package com.gc.api.customer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication(
  scanBasePackages = [
    "com.gc.common", "com.gc.api.customer"
  ]
)
@EnableMongoRepositories(
  basePackages = ["com.gc.common.adapter.out.persistence.jpa"]
)
class CalendarApplication

fun main(args: Array<String>) {
  runApplication<CalendarApplication>(*args)
}