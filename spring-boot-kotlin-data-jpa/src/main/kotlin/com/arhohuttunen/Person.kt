package com.arhohuttunen

import java.time.LocalDate
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Person(
    @Id
    @GeneratedValue
    val id: Long? = null,

    val name: String,
    val dateOfBirth: LocalDate
)
