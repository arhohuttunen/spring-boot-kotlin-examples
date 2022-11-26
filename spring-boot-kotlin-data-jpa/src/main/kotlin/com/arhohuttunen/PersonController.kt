package com.arhohuttunen

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid

@RestController
class PersonController(private val personRepository: PersonRepository) {
    @PostMapping("/person")
    fun createPerson(@Valid @RequestBody person: Person): Person {
        return personRepository.save(person)
    }

    @GetMapping("/person")
    fun findPeople(): List<Person> {
        return personRepository.findAll()
    }

    @GetMapping("/person/{id}")
    fun getPerson(@PathVariable id: Long): ResponseEntity<Person> {
        return ResponseEntity.of(personRepository.findById(id))
    }

    @DeleteMapping("/person/{id}")
    fun removePerson(@PathVariable id: Long) {
        personRepository.deleteById(id)
    }
}
