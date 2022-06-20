package com.school.schoolregister.controllers.grade

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.school.schoolregister.domain.entities.Grade
import com.school.schoolregister.domain.entities.inputGrade
import com.school.schoolregister.domain.inputs.GradeInput
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.grade.GradeService

@DgsComponent
class GradeDataFetcher(
    private val gradesService: GradeService
) {
    @DgsQuery
    fun grades(@InputArgument studentID: String): List<Grade> = gradesService.findByStudentID(studentID)

    @DgsQuery
    fun grade(@InputArgument id: String): Grade? =
        gradesService.findGradeByID(id)

    @DgsMutation(field = "remove_grade")
    fun removeGrade(@InputArgument id: String): RemoveResult<Grade> =
        gradesService.removeGradeByID(id)

    @DgsMutation(field = "update_grade")
    fun updateGrade(@InputArgument input: GradeInput): Grade? =
        gradesService.updateGrade(inputGrade(input)).updatedEntity
}