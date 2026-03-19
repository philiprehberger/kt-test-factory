package com.philiprehberger.testfactory

import kotlin.test.*

class FakerTest {
    @Test fun `name not empty`() = assertTrue(Faker.name().isNotEmpty())
    @Test fun `email contains at`() = assertTrue(Faker.email().contains("@"))
    @Test fun `uuid format`() = assertTrue(Faker.uuid().contains("-"))
    @Test fun `int in range`() { val v = Faker.int(10..20); assertTrue(v in 10..20) }
    @Test fun `pick`() { val v = Faker.pick("a", "b", "c"); assertTrue(v in listOf("a", "b", "c")) }
}
