package com.school.schoolregister.controllers

import com.school.schoolregister.entities.Student
import com.school.schoolregister.entities.generateRandomStudent
import com.school.schoolregister.services.StudentsService
import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId
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
        val students = generateSequence(5) {
            Student(
                name = generateRandomString(10),
                surname = generateRandomString(10),
                age = generateRandomInt()
            )
        }.toList()

        val before = studentsService.findStudentsCount()

        for (a in students)
            studentsService.saveStudent(a)

        assertThat(before).isLessThan(studentsService.findStudentsCount())

        // Chose one to remove
        val removeStudent = students[generateRandomInt(0, students.size)]

        // Remove assertions
        assertThat(studentsService.removeStudentById(removeStudent.id)).isEqualTo(removeStudent)
    }

    @Test
    fun `It should correctly find this student`() {
        val student = generateRandomStudent()
        val studentId = student.id

        assertThat(studentsService.saveStudent(student)).isEqualTo(student)
        assertThat(studentsService.findStudentById(studentId)).isEqualTo(student)

        assertThat(studentsService.removeStudentById(studentId)).isEqualTo(student)
    }

    @Test
    fun `It should correctly update this student`() {
        val student = generateRandomStudent()

        assertThat(studentsService.saveStudent(student)).isEqualTo(student)
        val update = Student(name = "Update", surname = "Test", age = student.age).apply {
            id = student.id
        }

        val updateResult = studentsService.updateStudent(update)
        assertThat(updateResult.updatedCount).isEqualTo(1)
        assertThat(updateResult.updatedEntity).isEqualTo(update)
        assertThat(updateResult.successful).isEqualTo(true)

        assertThat(studentsService.removeStudentById(student.id)).isEqualTo(update)
    }
}