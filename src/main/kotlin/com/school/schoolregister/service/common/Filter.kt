package com.school.schoolregister.service.common

import org.springframework.data.mongodb.core.MongoTemplate

/**
 * A generic filter
 */
abstract class Filter<T> {
    /**
     * Get filtered results
     */
    abstract fun filter(mongoTemplate: MongoTemplate): List<T>
}