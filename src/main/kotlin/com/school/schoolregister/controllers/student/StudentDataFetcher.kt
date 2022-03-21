package com.school.schoolregister.controllers.student

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.school.schoolregister.domain.entities.Student
import com.school.schoolregister.domain.inputs.StudentInput
import com.school.schoolregister.services.student.StudentService

@DgsComponent
class StudentDataFetcher(
    private val studentsService: StudentService
) {
    @DgsQuery
    fun students(@InputArgument name: String?, @InputArgument surname: String?): List<Student> {
        val data = studentsService.findStudents()

        return when {
            name != null -> data.filter { it.name == name }
            surname != null -> data.filter { it.surname == surname }
            else -> return data
        }
    }

    @DgsQuery
    fun student(@InputArgument id: String): Student? = studentsService.findStudentById(id)

    @DgsMutation(field = "add_student")
    fun addStudent(@InputArgument input: StudentInput): Student = studentsService.saveStudent(Student(input))

    @DgsMutation(field = "remove_student")
    fun removeStudent(@InputArgument id: String): Student? = studentsService.removeStudentById(id)
}