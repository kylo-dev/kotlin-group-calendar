package com.gc.storage.document.group_event

import com.gc.storage.document.common.BaseTimeEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.LocalTime

@Document("group_promises")
data class GroupPromiseDocument(
    val title: String,
    val groupId: String,
    val status: String, // TODO : ENUM
    val candidateEvents: String,
    val candidateStartTime: LocalTime,
    val candidateEndTime: LocalTime,
    val deadline: LocalDateTime,
    val hasTimer: Boolean,
    @Id
    val id: String? = null,
) : BaseTimeEntity()
