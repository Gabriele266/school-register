package com.school.schoolregister.controllers

import com.school.schoolregister.configuration.TestEntitiesConfiguration
import com.school.schoolregister.domain.Student
import com.school.schoolregister.domain.Vote
import com.school.schoolregister.domain.generateRandomStudent
import com.school.schoolregister.services.students.StudentsService
import com.school.schoolregister.services.votes.VotesService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(TestEntitiesConfiguration::class)
class VotesServiceTest @Autowired constructor(
    private val votesService: VotesService,
    private val studentsService: StudentsService,
    private val student: Student,
    private val vote: Vote
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

        val addedVote = updatedStudent?.votes?.first {
            it.studentID == updatedStudent.id
        }

        assertThat(addedVote).isNotNull
        assertThat(addedVote).isEqualTo(vote)

        studentsService.removeStudentById(student.id)
        votesService.removeVoteById(vote.id)
    }

    @Test
    fun `It should correctly return the details of this vote`() {
        val student = generateRandomStudent()
        val vote = Vote(
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