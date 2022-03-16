package com.school.schoolregister.services.presence

import com.school.schoolregister.domain.entities.Presence
import com.school.schoolregister.exceptions.InvalidStudentReferenceException
import com.school.schoolregister.exceptions.InvalidTeacherReferenceException
import com.school.schoolregister.services.common.CRUDService
import com.school.schoolregister.services.common.RemoveResult

interface PresenceService : CRUDService<Presence> {
    /**
     * Save a presence.
     *
     * @throws InvalidStudentReferenceException when an invalid student id is provided
     * @throws InvalidTeacherReferenceException when an invalid teacher id is provided
     */
    @Throws(InvalidStudentReferenceException::class, InvalidTeacherReferenceException::class)
    fun savePresence(presence: Presence): Presence

    /**
     * Update an existing presence
     */
    fun updatePresence(presence: Presence): Presence?

    /**
     * Checks if there is a presence with the given id
     */
    fun hasPresenceWithID(presenceID: String): Boolean

    /**
     * Remove a presence by its id
     */
    fun removePresenceByID(presenceID: String): RemoveResult<Presence>

    /**
     * Find a presence by its id
     *
     * @param presenceID Id to search
     */
    fun findPresenceById(presenceID: String): Presence?

    /**
     * Find all the presences of the student
     *
     * @param studentID The id of the student
     * @throws InvalidStudentReferenceException
     */
    @Throws(InvalidStudentReferenceException::class)
    fun findPresencesOfStudent(studentID: String): List<Presence>

    /**
     * Find all the times in witch the student was absent
     *
     * @param studentID The id of the student
     * @throws InvalidStudentReferenceException when an invalid student id is passed
     */
    @Throws(InvalidStudentReferenceException::class)
    fun findAbsencesOfStudent(studentID: String): List<Presence>
}