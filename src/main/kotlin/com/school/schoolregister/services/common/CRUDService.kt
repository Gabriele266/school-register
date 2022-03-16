package com.school.schoolregister.services.common

/**
 * Represents a generic crud service
 */
interface CRUDService<T> {
    /**
     * Find all the entities
     */
    fun findAll(): List<T>

    /**
     * Get the number of stored items
     */
    fun count(): Long
}