package com.philiprehberger.testfactory

/** A test data factory that builds instances of [T]. */
public class Factory<T> internal constructor(
    private val default: () -> T,
    private val traits: Map<String, (T) -> T>,
) {
    /** Build a default instance. */
    public fun build(): T = default()
    /** Build with an override. */
    public fun build(override: T.() -> T): T = default().override()
    /** Build with named traits applied in order. */
    public fun build(vararg traitNames: String): T {
        var result = default()
        for (name in traitNames) {
            val trait = traits[name] ?: throw IllegalArgumentException("Unknown trait: $name")
            result = trait(result)
        }
        return result
    }
    /** Build a list of [count] instances. */
    public fun buildList(count: Int): List<T> = (1..count).map { default() }
    /** Build a list with overrides. */
    public fun buildList(count: Int, override: T.() -> T): List<T> = (1..count).map { default().override() }
}

/** Build a factory with a DSL. */
public fun <T> factory(block: FactoryBuilder<T>.() -> Unit): Factory<T> {
    val builder = FactoryBuilder<T>()
    builder.block()
    return Factory(builder.defaultBuilder!!, builder.traits)
}

public class FactoryBuilder<T> {
    internal var defaultBuilder: (() -> T)? = null
    internal val traits = mutableMapOf<String, (T) -> T>()

    /** Set the default builder. */
    public fun default(builder: () -> T) { defaultBuilder = builder }
    /** Define a named trait. */
    public fun trait(name: String, transform: (T) -> T) { traits[name] = transform }
}
