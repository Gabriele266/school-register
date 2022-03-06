package com.school.schoolregister.services

data class UpdateResult<T>(
    val updatedCount: Int,
    val successful: Boolean,
    val updatedEntity: T? = null
) {
    companion object Factory {
        fun <T> failed(): UpdateResult<T> =
            UpdateResult(0, false)

        fun <T> successful(data: T): UpdateResult<T> =
            UpdateResult(1, true, data)
    }

    fun isSuccessful(): Boolean =
        successful && updatedCount > 0
}