package com.philiprehberger.testfactory

import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.UUID

/** Utility for generating random test data. */
public object Faker {
    private val random = kotlin.random.Random
    private val names = listOf("Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Hank", "Ivy", "Jack", "Kate", "Leo", "Mia", "Noah", "Olivia", "Paul", "Quinn", "Rose", "Sam", "Tina")
    private val sentences = listOf("The quick brown fox jumps.", "Lorem ipsum dolor sit amet.", "Hello world.", "Testing is important.", "Code quality matters.")

    /** Random first name. */
    public fun name(): String = names.random(random)
    /** Random email. */
    public fun email(): String = "${name().lowercase()}${random.nextInt(100, 999)}@test.com"
    /** Random UUID string. */
    public fun uuid(): String = UUID.randomUUID().toString()
    /** Random integer in range. */
    public fun int(range: IntRange = 1..1000): Int = random.nextInt(range.first, range.last + 1)
    /** Random sentence. */
    public fun sentence(): String = sentences.random(random)
    /** Pick a random value. */
    public fun <T> pick(vararg values: T): T = values[random.nextInt(values.size)]
    /** Random boolean with probability. */
    public fun boolean(probability: Double = 0.5): Boolean = random.nextDouble() < probability

    /** Random [Instant] between [from] and [to]. Defaults to the past year through now. */
    public fun timestamp(
        from: Instant = Instant.now().minus(365, ChronoUnit.DAYS),
        to: Instant = Instant.now(),
    ): Instant {
        val range = to.epochSecond - from.epochSecond
        val offset = random.nextLong(range + 1)
        return from.plusSeconds(offset)
    }

    /** Random [LocalDate] between [from] and [to]. Defaults to the past year through today. */
    public fun date(
        from: LocalDate = LocalDate.now().minusYears(1),
        to: LocalDate = LocalDate.now(),
    ): LocalDate {
        val days = to.toEpochDay() - from.toEpochDay()
        val offset = random.nextLong(days + 1)
        return from.plusDays(offset)
    }
}
