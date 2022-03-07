package com.school.schoolregister.services.teachers

import com.school.schoolregister.entities.Teacher
import com.school.schoolregister.repositories.TeacherRepository
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class TeachersServiceImpl(
    private val teacherRepository: TeacherRepository,
) : TeachersService {
    override fun saveTeacher(teacher: Teacher): Teacher =
        teacherRepository.save(teacher)

    override fun findTeacherById(id: String): Teacher? =
        teacherRepository.findById(ObjectId(id)).orElse(null)

    override fun findTeachers(): List<Teacher> =
        teacherRepository.findAll()

    override fun removeTeacherById(id: String): RemoveResult<Teacher> {
        val teacher = teacherRepository.findById(ObjectId(id)).orElse(null)

        return if (teacher != null) {
            teacherRepository.deleteById(ObjectId(id))

            RemoveResult.successful(teacher)
        } else RemoveResult.failed("Invalid teacher ID")
    }

    override fun findTeacherCount(): Long =
        teacherRepository.count()

    override fun updateTeacher(teacher: Teacher): UpdateResult<Teacher> {
        val teacherID = ObjectId(teacher.id)
        val original = teacherRepository.findById(teacherID).orElse(null)

        return if (original != null) {
            teacherRepository.deleteById(teacherID)

            UpdateResult.successful(teacher)
        } else UpdateResult.failed()
    }

    override fun hasTeacherWithId(id: String): Boolean =
        findTeacherById(id) != null

}