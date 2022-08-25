package com.school.schoolregister.controllers.grade

import com.school.schoolregister.domain.entities.Grade
import com.school.schoolregister.service.common.RemoveResult
import com.school.schoolregister.service.common.UpdateResult
import com.school.schoolregister.service.grade.GradeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/grades")
class GradeController(
    private val gradeService: GradeService
) {
    @Operation(
        summary = "Get all the grades of this school",
        description = "A simple way to query all the grades in this school. Will be soon deprecated"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "The operation is successful")
        ]
    )
    @GetMapping
    fun getVotes(): ResponseEntity<List<Grade>> =
        ResponseEntity.ok(
            gradeService.findAll()
        )

    @Operation(summary = "Save a list of grades")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "A list with all the grades to save")
    @PostMapping
    fun saveAllGrades(@RequestBody gradeList: List<Grade>): ResponseEntity<List<Grade>> =
        ResponseEntity.ok(gradeService.saveGrades(gradeList))

    @Operation(summary = "Remove a grade by id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "The grade was successfully removed"),
            ApiResponse(responseCode = "400", description = "Unable to find a grade with the given id")
        ]
    )
    @DeleteMapping("/{id}")
    fun removeVoteById(
        @Parameter(description = "Id of the grade to remove")
        @PathVariable id: String
    ): ResponseEntity<RemoveResult<Grade>> {
        val res = gradeService.removeGradeByID(id)

        return if (res.successful) ResponseEntity.ok(res)
        else return ResponseEntity.badRequest().body(res)
    }


    @Operation(summary = "Update an existing grade")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "The operation was successful"),
            ApiResponse(
                responseCode = "400",
                description = "There is no grade with the given id or the passed data is not valid"
            )
        ]
    )
    @PutMapping
    fun update(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data to update the existing grade. Note that the id should point to an existing grade")
        @RequestBody grade: Grade
    ): ResponseEntity<UpdateResult<Grade>> {
        val res = gradeService.updateGrade(grade)

        return if (res.successful) ResponseEntity.ok(res)
        else ResponseEntity.badRequest().body(res)
    }
}
