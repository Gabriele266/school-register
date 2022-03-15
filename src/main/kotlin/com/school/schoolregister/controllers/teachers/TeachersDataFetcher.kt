package com.school.schoolregister.controllers.teachers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.school.schoolregister.domain.entities.Teacher
import com.school.schoolregister.domain.inputs.TeacherInput
import com.school.schoolregister.domain.inputs.inputTeacher
import com.school.schoolregister.services.teachers.TeachersService

@DgsComponent
class TeachersDataFetcher(
    private val teachersService: TeachersService
) {
    @DgsQuery(field = "teachers")
    fun getTeachers(
        @InputArgument name: String?,
        @InputArgument surname: String?,
        @InputArgument subject: String?
    ): List<Teacher> {

        if (name != null)
            return teachersService.findTeachersWithName(name)

        if (surname != null)
            return teachersService.findTeachersWithSurname(surname)

        if (subject != null)
            return teachersService.findTeachersOfSubject(subject)

        return teachersService.findTeachers()
    }

    @DgsQuery
    fun teacher(@InputArgument id: String): Teacher? =
        teachersService.findTeacherById(id)

    @DgsMutation(field = "add_teacher")
    fun addTeacher(@InputArgument input: TeacherInput): Teacher =
        teachersService.saveTeacher(inputTeacher(input))

    @DgsMutation(field = "update_teacher")
    fun updateTeacher(@InputArgument input: TeacherInput): Teacher? =
        teachersService.updateTeacher(inputTeacher(input)).updatedEntity

    @DgsMutation(field = "remove_teacher")
    fun removeTeacher(@InputArgument id: String): Teacher? =
        teachersService.removeTeacherById(id).removed
}