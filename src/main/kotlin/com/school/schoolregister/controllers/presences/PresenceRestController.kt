package com.school.schoolregister.controllers.presences

import com.school.schoolregister.domain.entities.Presence
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.presence.PresenceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/presences")
class PresenceRestController(
    private val presenceService: PresenceService
) {
    @GetMapping
    fun getPresences(@RequestParam studentID: String?): ResponseEntity<List<Presence>> {
        return ResponseEntity.ok(
            if (studentID == null) presenceService.findAll()
            else presenceService.findPresencesOfStudent(studentID)
        )
    }

    @PostMapping
    fun addPresence(@RequestBody presence: Presence): ResponseEntity<Presence> = ResponseEntity.ok(
        presenceService.savePresence(presence)
    )

    @GetMapping("/{id}")
    fun getPresence(@PathVariable id: String): ResponseEntity<Presence?> = ResponseEntity.ok(
        presenceService.findPresenceById(id)
    )

    @DeleteMapping("/{id}")
    fun removePresence(@PathVariable id: String): ResponseEntity<RemoveResult<Presence>> =
        ResponseEntity.ok(presenceService.removePresenceByID(id))
}