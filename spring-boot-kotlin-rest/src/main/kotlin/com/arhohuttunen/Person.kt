package com.arhohuttunen

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class Person(
    @field:Min(1)
    val id: Long = 0,

    @field:NotBlank
    val name: String = ""
)
