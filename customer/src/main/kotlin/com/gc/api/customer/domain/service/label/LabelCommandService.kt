package com.gc.api.customer.domain.service.label

import com.gc.api.customer.application.port.out.persistence.label.ChangeMemberLabelPort
import com.gc.api.customer.application.service.dto.label.ChangeMemberLabelDto
import org.springframework.stereotype.Service

@Service
class LabelCommandService(
    val changeMemberLabelPort: ChangeMemberLabelPort
){

    fun changeMemberLabel(changeMemberLabelDto: ChangeMemberLabelDto) {
        changeMemberLabelPort.changeMemberLabel(changeMemberLabelDto)
    }
}