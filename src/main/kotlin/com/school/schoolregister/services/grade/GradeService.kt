package com.school.schoolregister.services.grade

import com.school.schoolregister.domain.entities.Grade
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult

/**
 * Service to manage all the grades
 */
interface GradeService {
    /**
     * Saves a new grade into the persistence support
     */
    fun saveGrade(grade: Grade): Grade

    /**
     * Saves a list of grades into the persistence support
     */
    fun saveGrades(gradesList: List<Grade>): List<Grade>

    /**
     * Updates a grade starting from its id. The first grade
     * that will match with the given one will be updated
     * @param grade The grade to update
     */
    fun updateGrade(grade: Grade): UpdateResult<Grade>

    /**
     * Removes a grade by its ID
     *
     * @param gradeID The grade id to remove
     * @return {RemoveResult} The result of the operation
     */
    fun removeGradeByID(gradeID: String): RemoveResult<Grade>

    /**
     * Checks if there is a grade with the given id
     * @param gradeID Id to search for
     */
    fun hasGradeWithID(gradeID: String): Boolean

    /**
     * Finds the first grade with the given id
     * @param gradeID The grade id
     */
    fun findGradeByID(gradeID: String): Grade?

    /**
     * Returns all the grades with the specified id
     */
    fun findAll(): List<Grade>

    /**
     * Returns the number of persisted grades
     */
    fun count(): Long

    /**
     * Find all the grades of a student, starting from its student
     * id
     *
     * @param studentID Student id to lookup for grades
     * @return All the grades of the student or an empty array
     */
    fun findByStudentID(studentID: String): Array<Grade>

    /**
     * Find all the grades added by a teacher by his id
     *
     * @param teacherID Id of the teacher
     * @return All the votes assigned by the teacher
     */
    fun findByTeacherID(teacherID: String): Array<Grade>
}