package com.arhohuttunen

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
class PersonController(private val personRepository: PersonRepository) {
    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    fun createPerson(@Valid @RequestBody person: Person): Person {
        return personRepository.savePerson(person)
    }

    @GetMapping("/person")
    fun findPeople(): List<Person> {
        return personRepository.findPeople()
    }

    @GetMapping("/person/{id}")
    fun getPerson(@PathVariable id: Long): Person {
        return personRepository.findPersonById(id)
    }

    @DeleteMapping("/person/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removePerson(@PathVariable id: Long) {
        personRepository.deletePersonById(id)
    }
}
