package com.school.schoolregister.services.grades

import com.school.schoolregister.domain.Grade
import com.school.schoolregister.exceptions.InvalidStudentReferenceException
import com.school.schoolregister.repositories.VotesRepository
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult
import com.school.schoolregister.services.students.StudentsService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class GradesServiceImpl(
    private val votesRepository: VotesRepository,
    private val studentsService: StudentsService,
) : GradesService {

    @Throws(InvalidStudentReferenceException::class)
    override fun saveVote(vote: Grade): Grade {
        val studentID = vote.studentID

        // Check if student reference is valid or throw exception
        studentsService.findStudentById(studentID) ?: throw InvalidStudentReferenceException(studentID, null)

        votesRepository.save(vote)

        return vote
    }

    override fun count(): Long =
        votesRepository.count()

    override fun updateVote(vote: Grade): UpdateResult<Grade> {
        val voteID = ObjectId(vote.id)

        val voteOriginal = votesRepository.findById(voteID).orElse(null)

        return if (voteOriginal != null) {
            votesRepository.deleteById(voteID)
            votesRepository.save(vote)
            UpdateResult.successful(vote)
        } else UpdateResult.failed()
    }

    override fun removeVoteById(voteId: String): RemoveResult<Grade> {
        val vote: Grade? = votesRepository.findById(ObjectId(voteId)).orElse(null)
        return if (vote != null) {
            votesRepository.deleteById(ObjectId(voteId))

            RemoveResult.successful(vote)
        } else RemoveResult.failed("Invalid remove id")
    }

    override fun hasVoteWithId(voteId: String): Boolean =
        votesRepository.findById(ObjectId(voteId)).isPresent

    override fun findVoteById(voteId: String): Grade? =
        votesRepository.findById(ObjectId(voteId)).orElse(null)

    override fun findAll(): List<Grade> =
        votesRepository.findAll()

}