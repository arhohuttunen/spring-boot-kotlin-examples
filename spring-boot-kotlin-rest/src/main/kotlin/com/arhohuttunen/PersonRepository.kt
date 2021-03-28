package com.arhohuttunen

interface PersonRepository {
    fun savePerson(person: Person): Person
    fun findPeople(): List<Person>
    fun findPersonById(id: Long): Person
    fun deletePersonById(id: Long)
}
