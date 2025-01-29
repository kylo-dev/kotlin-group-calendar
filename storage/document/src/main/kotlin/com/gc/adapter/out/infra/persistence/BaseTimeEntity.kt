package com.gc.adapter.out.infra.persistence

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

abstract class BaseTimeEntity {

  @get:CreatedDate
  abstract val createdAt: LocalDateTime

  @get:LastModifiedDate
  abstract val updatedAt: LocalDateTime
}