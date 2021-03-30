package com.arhohuttunen

import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long> {
    fun findPersonByName(name: String): Person
}
