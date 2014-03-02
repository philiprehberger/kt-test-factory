package com.philiprehberger.testfactory

import java.util.concurrent.atomic.AtomicInteger

/** Creates a lambda that returns incrementing values. */
public fun sequence(start: Int = 1, block: (Int) -> String): () -> String {
    val counter = AtomicInteger(start)
    return { block(counter.getAndIncrement()) }
}
