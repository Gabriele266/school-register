package com.school.schoolregister.services.teachers

import com.school.schoolregister.entities.Teacher
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult

interface TeachersService {
    fun saveTeacher(teacher: Teacher): Teacher
    fun findTeacherById(id: String): Teacher?
    fun findTeachers(): List<Teacher>
    fun removeTeacherById(id: String): RemoveResult<Teacher>
    fun findTeacherCount(): Long
    fun updateTeacher(teacher: Teacher): UpdateResult<Teacher>
    fun hasTeacherWithId(id: String): Boolean
}