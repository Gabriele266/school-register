package com.school.schoolregister.services.teachers

import com.school.schoolregister.domain.entities.Teacher
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult

/**
 * Service to manage all teachers
 */
interface TeachersService {
    /**
     * Saves a new teacher
     *
     * @param teacher Teacher to save
     */
    fun saveTeacher(teacher: Teacher): Teacher

    /**
     * Finds a teacher by his id
     *
     * @param id Id to search for
     * @return The teacher with the given id or null if it does not exist
     */
    fun findTeacherById(id: String): Teacher?

    /**
     * Find all teachers in this server
     */
    fun findTeachers(): List<Teacher>

    /**
     * Removes a teacher by the id
     *
     * @param id Id to use for removing the teacher
     */
    fun removeTeacherById(id: String): RemoveResult<Teacher>

    /**
     * Find the teacher count
     */
    fun count(): Long

    /**
     * Update an existing teacher with some new data
     */
    fun updateTeacher(teacher: Teacher): UpdateResult<Teacher>

    /**
     * Check if there is a teacher with the given id
     */
    fun hasTeacherWithId(id: String): Boolean
}