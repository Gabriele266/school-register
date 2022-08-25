package com.school.schoolregister.service.student

import com.school.schoolregister.domain.entities.Student
import com.school.schoolregister.service.common.UpdateResult

/**
 * Service to manage all the students
 */
interface StudentService {
    /**
     * Save a new student. Sends an email to welcome the new student
     *
     * @param input Student to save
     */
    fun saveStudent(input: Student): Student

    /**
     * Find the student with the given id
     *
     * @param id Id to search
     * @return The student or null if it does not exist
     */
    fun findStudentById(id: String): Student?

    /**
     * Find all the students
     */
    fun findStudents(): List<Student>

    /**
     * Remove a student by id
     * Sends an email to goodbye the student
     *
     * @param id id to remove
     */
    fun removeStudentById(id: String): Student?

    /**
     * Count all the students
     */
    fun count(): Long

    /**
     * Update an existing student
     *
     * @param student data to update
     * @return The result of this operation
     */
    fun updateStudent(student: Student): UpdateResult<Student>

    /**
     * Checks if exists a student with the given id
     *
     * @param id Id to search
     */
    fun hasStudentWithId(id: String): Boolean

    /**
     * Finds the first student
     */
    fun findFirst(): Student?
}