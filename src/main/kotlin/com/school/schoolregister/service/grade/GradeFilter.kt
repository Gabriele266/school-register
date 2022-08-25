package com.school.schoolregister.service.grade

import com.school.schoolregister.domain.entities.Grade
import com.school.schoolregister.service.common.Filter
import org.springframework.data.mongodb.core.MongoTemplate

data class GradeFilter(
    val studentID: String?,
    val teacherID: String?,
    val valueGT: Int?
) : Filter<Grade>() {
    override fun filter(mongoTemplate: MongoTemplate): List<Grade> {
        return mongoTemplate.findAll(Grade::class.java)
    }
}