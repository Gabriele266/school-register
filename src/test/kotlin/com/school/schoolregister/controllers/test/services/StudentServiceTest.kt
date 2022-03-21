package com.school.schoolregister.controllers.test.services

import com.school.schoolregister.controllers.generateRandomInt
import com.school.schoolregister.controllers.generateRandomString
import com.school.schoolregister.domain.entities.Student
import com.school.schoolregister.domain.utils.generateRandomStudent
import com.school.schoolregister.services.student.StudentService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.time.ZoneOffset

@SpringBootTest
class StudentServiceTest(
    @Autowired
    private val studentService: StudentService
) {
    @Test
    fun `It should correctly add a new student`() {
        val allStudentsBefore = studentService.findStudents().size

        val addStudent = Student(name = "TestS", surname = "TestSur", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
        assertThat(studentService.saveStudent(addStudent)).isEqualTo(addStudent)
        val allStudentsAfter = studentService.findStudents().size

        val allStudents = studentService.findStudents()
        assertThat(allStudentsAfter).isGreaterThan(allStudentsBefore)
        assertThat(allStudents).isNotEmpty
        assertThat(allStudents).contains(addStudent)
    }

    @Test
    fun `It should correctly remove a student`() {
        // Add some students
        val students = com.school.schoolregister.controllers.generateSequence(5) {
            Student(
                name = generateRandomString(10),
                surname = generateRandomString(10),
            )
        }.toList()

        val before = studentService.count()

        for (a in students)
            studentService.saveStudent(a)

        assertThat(before).isLessThan(studentService.count())

        // Chose one to remove
        val removeStudent = students[generateRandomInt(0, students.size)]

        // Remove assertions
        assertThat(studentService.removeStudentById(removeStudent.id)).isEqualTo(removeStudent)
    }

    @Test
    fun `It should correctly find this student`() {
        val student = generateRandomStudent()
        val studentId = student.id

        assertThat(studentService.saveStudent(student)).isEqualTo(student)
        assertThat(studentService.findStudentById(studentId)).isEqualTo(student)

        assertThat(studentService.removeStudentById(studentId)).isEqualTo(student)
    }

    @Test
    fun `It should correctly update this student`() {
        val student = generateRandomStudent()

        assertThat(studentService.saveStudent(student)).isEqualTo(student)
        val update = Student(name = "Update", surname = "Test").apply {
            id = student.id
        }

        val updateResult = studentService.updateStudent(update)
        assertThat(updateResult.updatedCount).isEqualTo(1)
        assertThat(updateResult.updatedEntity).isEqualTo(update)
        assertThat(updateResult.successful).isEqualTo(true)

        assertThat(studentService.removeStudentById(student.id)).isEqualTo(update)
    }
}