package com.arhohuttunen

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Person(
    @Id
    @GeneratedValue
    val id: Long? = null,

    val name: String,
    val dateOfBirth: LocalDate
)
