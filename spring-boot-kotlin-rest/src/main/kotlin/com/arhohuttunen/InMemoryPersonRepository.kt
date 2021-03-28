package com.arhohuttunen

import org.springframework.stereotype.Component

@Component
class InMemoryPersonRepository : PersonRepository {
    private val people: MutableMap<Long, Person> = mutableMapOf()

    override fun savePerson(person: Person): Person {
        people[person.id] = person
        return person
    }

    override fun findPeople(): List<Person> {
        return people.values.toList()
    }

    override fun findPersonById(id: Long): Person {
        return people[id] ?: throw PersonNotFound(id)
    }

    override fun deletePersonById(id: Long) {
        people.remove(id) ?: throw PersonNotFound(id)
    }
}
