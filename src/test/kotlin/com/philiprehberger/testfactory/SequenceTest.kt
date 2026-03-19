package com.philiprehberger.testfactory

import kotlin.test.*

class SequenceTest {
    @Test fun `auto increments`() {
        val seq = sequence { "user-$it" }
        assertEquals("user-1", seq())
        assertEquals("user-2", seq())
        assertEquals("user-3", seq())
    }
}
