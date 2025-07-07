package com.gc.api.customer.domain.model.group

import com.gc.storage.document.group.GroupDocument

data class Group(
    val id: String,
    val name: String,
    val profile: String,
    val description: String,
    val link: String,
) {
    companion object {
        fun loadFromDocument(groupDocument: GroupDocument): Group {
            return Group(
                id = groupDocument.id!!,
                name = groupDocument.name,
                profile = groupDocument.profile,
                description = groupDocument.description,
                link = groupDocument.link
            )
        }
    }
}
