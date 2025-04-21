package com.gc.api.customer.application.port.out.persistence.label

import com.gc.api.customer.application.service.dto.label.ChangeMemberLabelDto

interface ChangeMemberLabelPort {

    fun changeMemberLabel(changeMemberLabelDto: ChangeMemberLabelDto)
}