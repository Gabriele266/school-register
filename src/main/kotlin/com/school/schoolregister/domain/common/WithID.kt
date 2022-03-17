package com.school.schoolregister.domain.common

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Represents an entity with an ID and exposes a way to retreive it and make comparisons
 *
 */
@Document
open class WithID(
    @Id
    var id: String = ObjectId.get().toHexString()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WithID

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String =
        "Object with id $id"
}