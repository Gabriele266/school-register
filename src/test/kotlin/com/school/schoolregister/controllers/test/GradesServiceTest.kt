package com.school.schoolregister.controllers.test

import com.school.schoolregister.configuration.TestEntitiesConfiguration
import com.school.schoolregister.controllers.currentDateTimeMillis
import com.school.schoolregister.controllers.generateRandomString
import com.school.schoolregister.domain.Grade
import com.school.schoolregister.domain.Student
import com.school.schoolregister.domain.generateRandomStudent
import com.school.schoolregister.services.grades.GradesService
import com.school.schoolregister.services.students.StudentsService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(TestEntitiesConfiguration::class)
class GradesServiceTest @Autowired constructor(
    private val votesService: GradesService,
    private val studentsService: StudentsService,
    private val student: Student,
    private val vote: Grade
) {

    @Test
    fun `It should correctly return all votes`() {
        val votes = votesService.findAll()

        assertThat(votes).isNotNull
    }

    @Test
    fun `It should correctly add a new vote to a student`() {
        vote.studentID = student.id

        studentsService.saveStudent(student)

        val beforeCount = votesService.count()
        assertThat(votesService.saveVote(vote)).isEqualTo(vote)
        assertThat(beforeCount).isLessThan(votesService.count())

        val updatedStudent = studentsService.findStudentById(vote.studentID)

        assertThat(updatedStudent).isNotNull

        studentsService.removeStudentById(student.id)
        votesService.removeVoteById(vote.id)
    }

    @Test
    fun `It should correctly return the details of this vote`() {
        val student = generateRandomStudent()
        val vote = Grade(
            student.id,
            value = 4F,
            dateTime = currentDateTimeMillis(),
            teacherID = "ww",
            description = generateRandomString()
        )

        studentsService.saveStudent(student)
        votesService.saveVote(vote)

        val foundVote = votesService.findVoteById(vote.id)
        assertThat(foundVote).isNotNull
        assertThat(foundVote).isEqualTo(vote)

        if (foundVote != null) {
            val linkedStudent = studentsService.findStudentById(foundVote.studentID)
            assertThat(linkedStudent).isNotNull
            assertThat(linkedStudent?.name).isEqualTo(student.name)
            assertThat(linkedStudent?.surname).isEqualTo(student.surname)
        }

        // Remove garbage stuff
        studentsService.removeStudentById(student.id)
        votesService.removeVoteById(vote.id)
    }
}