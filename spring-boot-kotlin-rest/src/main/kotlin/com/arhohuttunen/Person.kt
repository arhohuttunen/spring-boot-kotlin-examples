package com.arhohuttunen

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class Person(
    @field:Min(1)
    val id: Long = 0,

    @field:NotBlank
    val name: String = ""
)
