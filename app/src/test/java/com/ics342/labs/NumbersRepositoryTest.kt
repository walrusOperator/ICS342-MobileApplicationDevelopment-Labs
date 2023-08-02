package com.ics342.labs

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.UUID
import kotlin.random.Random

internal class NumbersRepositoryTest {

    @Test
    fun `If database does not have a number fetch it from the Api`() {
        // Setup
        val database = mockk<Database>()
        val api = mockk<Api>()
        val number = Number(UUID.randomUUID().toString(), Random.nextInt())
        val id = number.id

        every { database.getNumber(id) } returns null
        every { api.getNumber(id) } returns number

        // Act
        val repository = NumbersRepository(database, api)
        val result = repository.getNumber(id)

        // Assert
        assertNotNull(result)
        assertEquals(result, number)

        verify { database.getNumber(id) }
        verify { api.getNumber(id) }
    }

    @Test
    fun ifDatabaseIsEmptyShouldFetchNumbersFromApi() {
        val db = mockk<Database>()
        val api = mockk<Api>()
        val number = Number(UUID.randomUUID().toString(), Random.nextInt())

        every{db.getAllNumbers()} returns listOf()
        every{api.getNumbers()} returns listOf(number)
        every{db.storeNumbers(listOf(number))} just runs

        val repository = NumbersRepository(db, api)
        val result = repository.fetchNumbers()

        assertNotNull(result)
        assertEquals(listOf(number), repository.fetchNumbers())

        verify{db.getAllNumbers()}
        verify{api.getNumbers()}
    }
}
