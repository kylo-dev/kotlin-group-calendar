package com.gc.storage.framework.config

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

@Component
@WritingConverter
class LocalDateTimeToDateKstConverter : Converter<LocalDateTime, Date> {
  override fun convert(source: LocalDateTime): Date = Timestamp.valueOf(source.plusHours(9))
}