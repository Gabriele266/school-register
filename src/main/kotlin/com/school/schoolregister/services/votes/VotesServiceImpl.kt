package com.school.schoolregister.services.votes

import com.school.schoolregister.entities.Vote
import com.school.schoolregister.repositories.StudentsRepository
import com.school.schoolregister.repositories.VotesRepository
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult
import com.school.schoolregister.services.students.StudentsService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class VotesServiceImpl(
    private val votesRepository: VotesRepository,
    private val studentsService: StudentsService
) : VotesService {
    override fun saveVote(vote: Vote): Vote {
        val studentID = vote.studentID
        votesRepository.save(vote)

        val stud = studentsService.findStudentById(studentID)

        if (stud != null) {
            stud.votes.add(vote)
            studentsService.updateStudent(stud)
        }

        return vote
    }

    override fun count(): Long =
        votesRepository.count()

    override fun updateVote(vote: Vote): UpdateResult<Vote> {
        val voteID = ObjectId(vote.id)

        val voteOriginal = votesRepository.findById(voteID).orElse(null)

        return if (voteOriginal != null) {
            votesRepository.deleteById(voteID)
            votesRepository.save(vote)
            UpdateResult.successful(vote)
        } else UpdateResult.failed()
    }

    override fun removeVoteById(voteId: String): RemoveResult<Vote> {
        val vote: Vote? = votesRepository.findById(ObjectId(voteId)).orElse(null)
        return if (vote != null) {
            val studentID = vote.studentID
            votesRepository.deleteById(ObjectId(voteId))
            val student = studentsService.findStudentById(studentID)

            if (student != null) {
                student.votes.removeIf {
                    it.studentID == studentID
                }
                studentsService.updateStudent(student)
            }

            RemoveResult.successful(vote)
        } else RemoveResult.failed("Invalid remove id")
    }

    override fun hasVoteWithId(voteId: String): Boolean =
        votesRepository.findById(ObjectId(voteId)).isPresent

    override fun findVoteById(voteId: String): Vote? =
        votesRepository.findById(ObjectId(voteId)).orElse(null)

    override fun findAll(): List<Vote> =
        votesRepository.findAll()

}