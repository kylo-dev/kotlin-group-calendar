package com.gc.storage.framework.config

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
@ReadingConverter
class DateToLocalDateTimeKstConverter : Converter<Date, LocalDateTime> {
    override fun convert(source: Date): LocalDateTime =
        source.toInstant()
            .atZone(ZoneId.of("Asia/Seoul"))
            .toLocalDateTime()
            .minusHours(9)
}

@Component
@WritingConverter
class LocalDateTimeToDateKstConverter : Converter<LocalDateTime, Date> {
    override fun convert(source: LocalDateTime): Date =
        Timestamp.valueOf(source.plusHours(9))
}
