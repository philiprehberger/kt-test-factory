package com.philiprehberger.testfactory

import kotlin.test.*

data class TestUser(val id: String, val name: String, val role: String = "user")

class FactoryTest {
    private val userFactory = factory<TestUser> {
        default { TestUser(id = Faker.uuid(), name = Faker.name()) }
        trait("admin") { it.copy(role = "admin") }
    }

    @Test fun `build default`() { val u = userFactory.build(); assertTrue(u.id.isNotEmpty()); assertEquals("user", u.role) }
    @Test fun `build with override`() { val u = userFactory.build { copy(name = "Custom") }; assertEquals("Custom", u.name) }
    @Test fun `build with trait`() { val u = userFactory.build("admin"); assertEquals("admin", u.role) }
    @Test fun `buildList`() { val list = userFactory.buildList(5); assertEquals(5, list.size) }
    @Test fun `unknown trait throws`() { assertFailsWith<IllegalArgumentException> { userFactory.build("nonexistent") } }
}
