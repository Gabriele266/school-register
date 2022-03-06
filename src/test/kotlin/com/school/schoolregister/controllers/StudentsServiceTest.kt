package com.school.schoolregister.controllers

import com.school.schoolregister.entities.Student
import com.school.schoolregister.services.StudentsService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StudentsServiceTest(
    @Autowired
    private val studentsService: StudentsService
) {
    @Test
    fun `It should correctly add a new student`() {
        val allStudentsBefore = studentsService.findStudents().size

        val addStudent = Student(name = "TestS", surname = "TestSur", age = 45)
        assertThat(studentsService.saveStudent(addStudent)).isEqualTo(addStudent)
        val allStudentsAfter = studentsService.findStudents().size

        val allStudents = studentsService.findStudents()
        assertThat(allStudentsAfter).isGreaterThan(allStudentsBefore)
        assertThat(allStudents).isNotEmpty
        assertThat(allStudents).contains(addStudent)
    }

    @Test
    fun `It should correctly remove a student`() {
        // Add some students
        val students = listOf(Student(name = "Test1", surname = "Test2", age = 22))

        // Chose one to remove
        // Remove assertions

    }
}