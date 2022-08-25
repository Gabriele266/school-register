package com.school.schoolregister.service.teacher

import com.school.schoolregister.domain.entities.Teacher
import com.school.schoolregister.service.common.RemoveResult
import com.school.schoolregister.service.common.UpdateResult

/**
 * Service to manage all teachers
 */
interface TeacherService {
    /**
     * Saves a new teacher
     *
     * @param teacher Teacher to save
     */
    fun save(teacher: Teacher): Teacher

    /**
     * Save all teachers
     */
    fun saveAll(data: List<Teacher>): List<Teacher>

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

    /**
     * Find all the teachers with the given name
     */
    fun findTeachersWithName(name: String): List<Teacher>

    /**
     * Find all the teachers with the given surname
     */
    fun findTeachersWithSurname(surname: String): List<Teacher>

    /**
     * Find all the teachers of a specific subject
     *
     * The subject is compared using regex (start with)
     */
    fun findTeachersOfSubject(subject: String): List<Teacher>
}