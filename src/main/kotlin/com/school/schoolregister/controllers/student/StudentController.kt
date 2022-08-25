package com.school.schoolregister.controllers.student

import com.school.schoolregister.domain.entities.Grade
import com.school.schoolregister.domain.entities.Student
import com.school.schoolregister.domain.utils.studentIsValid
import com.school.schoolregister.service.common.UpdateResult
import com.school.schoolregister.service.grade.GradeService
import com.school.schoolregister.service.student.StudentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/students")
class StudentController(
    private val studentService: StudentService,
    private val gradeService: GradeService
) {
    @Operation(summary = "Get all the students of this school")
    @GetMapping
    fun getStudents(): List<Student> =
        studentService.findStudents()


    @Operation(
        summary = "Save a student",
        description = "Note: this endpoint only accepts one student per time",
        deprecated = true
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "A student to save")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "The student is valid and correctly saved"),
            ApiResponse(responseCode = "400", description = "The student contains invalid data")
        ]
    )
    @PostMapping
    fun addStudent(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The student to save") @RequestBody student: Student): ResponseEntity<Any> =
        if (studentIsValid(student)) ResponseEntity.ok(studentService.saveStudent(student))
        else ResponseEntity.badRequest().body("Invalid student input")

    @Operation(summary = "Get a student by id", description = "Get the details of a student by specifing the id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "The student with the given id effectively exists and his data will be correctly returned. "
            ),
            ApiResponse(
                responseCode = "400", description = "There is no student with the given id. ", content = [
                ]
            )
        ]
    )
    @GetMapping("/{id}")
    fun getStudentById(
        @Parameter(description = "Id of the student to get details of", required = true)
        @PathVariable id: String
    ): ResponseEntity<Any?> =
        if (studentService.hasStudentWithId(id)) ResponseEntity.ok(studentService.findStudentById(id))
        else ResponseEntity.badRequest().body("Invalid id")

    @Operation(summary = "Delete a student by id", description = "Search and remove a student by passing in the id. ")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "The student with the given id effectively exists and his data will be correctly removed. "
            ),
            ApiResponse(
                responseCode = "400", description = "There is no student with the given id. ", content = [
                ]
            )
        ]
    )
    @DeleteMapping("/{id}")
    fun removeStudentById(@PathVariable id: String): ResponseEntity<Student?> =
        if (studentService.hasStudentWithId(id)) ResponseEntity.ok(studentService.removeStudentById(id))
        else ResponseEntity.badRequest().body(null)


    @Operation(
        summary = "Update a student",
        deprecated = true,
        description = "IMPORTANT NOTE: This method is deprecated. Use PUT /student/{id} to update instead"
    )
    @PostMapping("/update")
    fun updateStud(@RequestBody student: Student): ResponseEntity<UpdateResult<Student>> {
        val updateResult = studentService.updateStudent(student)

        return if (updateResult.isSuccessful()) ResponseEntity.ok(updateResult)
        else ResponseEntity.badRequest().body(updateResult)
    }

    @Operation(
        summary = "Get all the grades of a student",
        description = "This endpoint provides access to all the grades of 1 student. It is also possible to specify a filter (on teacher)"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "The student effectively exists. Content: Array of grades"),
            ApiResponse(responseCode = "400", description = "The student with the given id does not exist")
        ]
    )
    @GetMapping("/{id}/grades")
    fun getStudentGrades(
        @Parameter(name = "id", description = "The student id of witch we're searching the grades")
        @PathVariable id: String,
        @Parameter(description = "Optional filter with the teacher id. Only the grades put by that teacher will be returned")
        @RequestParam(name = "teacher") teacherId: String?
    ): List<Grade> {
        val result = gradeService.findByStudentID(id)

        if (teacherId != null)
            return result.filter { it.teacherID == teacherId }

        return result
    }
}