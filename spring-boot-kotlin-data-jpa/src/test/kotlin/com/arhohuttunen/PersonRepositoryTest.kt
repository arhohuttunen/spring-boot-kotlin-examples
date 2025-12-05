package com.arhohuttunen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PersonRepositoryTest(
    @Autowired
    private val entityManager: TestEntityManager,
    @Autowired
    private val personRepository: PersonRepository
) {
    @Test
    fun `already persisted person can be found by name`() {
        val person = Person(name = "John Doe", dateOfBirth = LocalDate.of(2003, 3, 30))
        entityManager.persist(person)

        assertThat(personRepository.findPersonByName("John Doe")).isNotNull
    }
}
