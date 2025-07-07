package com.gc.storage.document.group_event

import com.gc.storage.document.common.BaseTimeEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("group_events")
data class GroupEventDocument(
    val groupPromiseId: String,
    val isAllDay: Boolean,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    @Id
    val id: String? = null,
) : BaseTimeEntity()
