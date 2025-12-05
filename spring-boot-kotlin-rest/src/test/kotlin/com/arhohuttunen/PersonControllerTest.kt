package com.arhohuttunen

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import tools.jackson.databind.ObjectMapper

@WebMvcTest(PersonController::class)
class PersonControllerTest(
    @Autowired
    private val mockMvc: MockMvc,
    @Autowired
    private val mapper: ObjectMapper
){
    @MockkBean(relaxed = true)
    private lateinit var personRepository: PersonRepository

    @Test
    fun `creating a person with POST returns HTTP Created on success`() {
        val person = Person(1, "Arho")

        mockMvc.post("/person") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(person)
        }.andExpect {
            status { isCreated() }
        }
    }

    @Test
    fun `creating a person with POST returns created representation on success`() {
        val person = Person(1, "Arho")

        every { personRepository.savePerson(person) } returns person

        mockMvc.post("/person") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(person)
        }.andExpect {
            jsonPath("$.id") { value(1) }
            jsonPath("$.name") { value("Arho") }
        }
    }

    @Test
    fun `creating a person with POST returns HTTP Bad Request when validation fails`() {
        val person = Person(1, "")

        mockMvc.post("/person") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(person)
        }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    fun `creating a person with POST returns an error when validation fails`() {
        val person = Person(1, "")

        mockMvc.post("/person") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(person)
        }.andExpect {
            jsonPath("$.violations") { isNotEmpty() }
            jsonPath("$.violations[0].field") { value("name") }
            jsonPath("$.violations[0].message") { value("must not be blank") }
        }
    }

    @Test
    fun `listing people with GET returns a list of people on success`() {
        val person = Person(1, "Arho")

        every { personRepository.findPeople() } returns listOf(person)

        mockMvc.get("/person")
            .andExpect {
                jsonPath("$.[0].id") { value(1) }
                jsonPath("$.[0].name") { value("Arho") }
            }
    }

    @Test
    fun `getting a person with GET returns persons details on success`() {
        val person = Person(1, "Arho")

        every { personRepository.findPersonById(1) } returns person

        mockMvc.get("/person/{id}", 1)
            .andExpect {
                jsonPath("$.id") { value(1) }
                jsonPath("$.name") { value("Arho") }
            }
    }

    @Test
    fun `getting a person with GET returns HTTP Not Found when person does not exist`() {
        every { personRepository.findPersonById(1) } throws PersonNotFound(1)

        mockMvc.get("/person/{id}", 1)
            .andExpect {
                status { isNotFound() }
            }
    }

    @Test
    fun `removing a person with DELETE returns HTTP No Content on success`() {
        mockMvc.delete("/person/{id}", 1)
            .andExpect {
                status { isNoContent() }
            }
    }

    @Test
    fun `removing a person with DELETE returns HTTP Not Found when person does not exist`() {
        every { personRepository.deletePersonById(1) } throws PersonNotFound(1)

        mockMvc.delete("/person/{id}", 1)
            .andExpect {
                status { isNotFound() }
            }
    }
}
