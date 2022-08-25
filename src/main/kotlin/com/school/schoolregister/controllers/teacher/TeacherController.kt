package com.school.schoolregister.controllers.teacher

import com.school.schoolregister.domain.entities.Teacher
import com.school.schoolregister.service.common.RemoveResult
import com.school.schoolregister.service.teacher.TeacherService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/teachers")
class TeacherController(
    private val teachersService: TeacherService
) {
    @Operation(summary = "Get all the teachers in this school")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "A list of all the teachers in this school")
        ]
    )
    @GetMapping
    fun getTeachers(): ResponseEntity<List<Teacher>> =
        ResponseEntity.ok(teachersService.findTeachers())

    @Operation(summary = "Add a new teacher", description = "Add one or more teacher by passing the data. ")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "All the passed teachers contain valid data and there was no problem"
            ),
            ApiResponse(responseCode = "400", description = "Some teachers contain invalid data")
        ]
    )
    @PostMapping
    fun addTeacher(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "List of teachers to be added")
        @RequestBody teachers: List<Teacher>
    ): ResponseEntity<List<Teacher>> =
        ResponseEntity.ok(teachersService.saveAll(teachers))

    @Operation(summary = "Get a teacher by id", description = "Get the details of a teacher by specifing the id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "The teacher with the given id effectively exists and his data will be correctly returned. "
            ),
            ApiResponse(
                responseCode = "400", description = "There is no teacher with the given id. ", content = [
                ]
            )
        ]
    )
    @GetMapping("/{id}")
    fun getTeacherDetails(
        @Parameter(description = "Id of the teacher to get details of", required = true)
        @PathVariable id: String
    ): ResponseEntity<Teacher?> {
        val teacherDetail = teachersService.findTeacherById(id)

        return if (teacherDetail != null)
            ResponseEntity.ok(teacherDetail)
        else ResponseEntity.badRequest().body(null)
    }

    @Operation(summary = "Delete a teacher by id", description = "Search and remove a teacher by passing in the id. ")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "The teacher with the given id effectively exists and his data will be correctly removed. "
            ),
            ApiResponse(
                responseCode = "400", description = "There is no teacher with the given id. ", content = [
                ]
            )
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteTeacherById(
        @Parameter(description = "Id of the teacher to remove")
        @PathVariable id: String
    ): ResponseEntity<RemoveResult<Teacher>> {
        val result = teachersService.removeTeacherById(id)

        return if (result.successful) ResponseEntity.ok(result)
        else ResponseEntity.badRequest().body(result)
    }
}