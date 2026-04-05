package com.philiprehberger.testfactory

import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.test.*

class FakerTest {
    @Test fun `name not empty`() = assertTrue(Faker.name().isNotEmpty())
    @Test fun `email contains at`() = assertTrue(Faker.email().contains("@"))
    @Test fun `uuid format`() = assertTrue(Faker.uuid().contains("-"))
    @Test fun `int in range`() { val v = Faker.int(10..20); assertTrue(v in 10..20) }
    @Test fun `pick`() { val v = Faker.pick("a", "b", "c"); assertTrue(v in listOf("a", "b", "c")) }

    @Test fun `timestamp within default range`() {
        val before = Instant.now().minus(365, ChronoUnit.DAYS)
        val result = Faker.timestamp()
        val after = Instant.now()
        assertTrue(result >= before && result <= after)
    }

    @Test fun `timestamp within custom range`() {
        val from = Instant.parse("2025-01-01T00:00:00Z")
        val to = Instant.parse("2025-01-02T00:00:00Z")
        val result = Faker.timestamp(from, to)
        assertTrue(result >= from && result <= to)
    }

    @Test fun `date within default range`() {
        val before = LocalDate.now().minusYears(1)
        val result = Faker.date()
        val after = LocalDate.now()
        assertTrue(!result.isBefore(before) && !result.isAfter(after))
    }

    @Test fun `date within custom range`() {
        val from = LocalDate.of(2025, 6, 1)
        val to = LocalDate.of(2025, 6, 30)
        val result = Faker.date(from, to)
        assertTrue(!result.isBefore(from) && !result.isAfter(to))
    }
}
