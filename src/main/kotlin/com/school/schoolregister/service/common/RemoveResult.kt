package com.school.schoolregister.service.common

class RemoveResult<T>(
    val removeCount: Int,
    val successful: Boolean,
    val removed: T?,
    val errors: String?
) {
    companion object {
        fun <T> successful(data: T): RemoveResult<T> =
            RemoveResult(1, true, data, null)

        fun <T> failed(errors: String?): RemoveResult<T> =
            RemoveResult(0, false, null, errors)
    }
}