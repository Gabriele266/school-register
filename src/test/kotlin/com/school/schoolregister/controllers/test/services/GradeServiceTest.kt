package com.school.schoolregister.controllers.test.services

import com.school.schoolregister.configuration.TestEntitiesConfiguration
import com.school.schoolregister.controllers.currentDateTimeMillis
import com.school.schoolregister.controllers.generateRandomString
import com.school.schoolregister.domain.entities.Grade
import com.school.schoolregister.domain.entities.Student
import com.school.schoolregister.domain.utils.generateRandomStudent
import com.school.schoolregister.service.grade.GradeService
import com.school.schoolregister.service.student.StudentService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(TestEntitiesConfiguration::class)
class GradeServiceTest @Autowired constructor(
    private val gradeService: GradeService,
    private val studentService: StudentService,
    private val student: Student,
    private val grade: Grade
) {

    @Test
    fun `It should correctly return all votes`() {
        val grades = gradeService.findAll()

        assertThat(grades).isNotNull
    }

    @Test
    fun `It should correctly add a new vote to a student`() {
        grade.studentID = student.id

        studentService.saveStudent(student)

        val beforeCount = gradeService.count()
        assertThat(gradeService.saveGrade(grade)).isEqualTo(grade)
        assertThat(beforeCount).isLessThan(gradeService.count())

        val updatedStudent = studentService.findStudentById(grade.studentID)

        assertThat(updatedStudent).isNotNull

        studentService.removeStudentById(student.id)
        gradeService.removeGradeByID(grade.id)
    }

    @Test
    fun `It should correctly return the details of this vote`() {
        val student = generateRandomStudent()
        val grade = Grade(
            student.id,
            value = 4F,
            dateTime = currentDateTimeMillis(),
            teacherID = "ww",
            description = generateRandomString(),
            subject = "Some subject"
        )

        studentService.saveStudent(student)
        gradeService.saveGrade(grade)

        val foundVote = gradeService.findGradeByID(grade.id)
        assertThat(foundVote).isNotNull
        assertThat(foundVote).isEqualTo(grade)

        if (foundVote != null) {
            val linkedStudent = studentService.findStudentById(foundVote.studentID)
            assertThat(linkedStudent).isNotNull
            assertThat(linkedStudent?.name).isEqualTo(student.name)
            assertThat(linkedStudent?.surname).isEqualTo(student.surname)
        }

        // Remove garbage stuff
        studentService.removeStudentById(student.id)
        gradeService.removeGradeByID(grade.id)
    }
}