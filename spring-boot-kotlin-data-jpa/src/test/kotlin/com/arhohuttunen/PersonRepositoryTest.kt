package com.arhohuttunen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull
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
