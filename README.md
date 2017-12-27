# test-factory

[![Tests](https://github.com/philiprehberger/kt-test-factory/actions/workflows/publish.yml/badge.svg)](https://github.com/philiprehberger/kt-test-factory/actions/workflows/publish.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.philiprehberger/test-factory)](https://central.sonatype.com/artifact/com.philiprehberger/test-factory)
[![License](https://img.shields.io/github/license/philiprehberger/kt-test-factory)](LICENSE)
[![Sponsor](https://img.shields.io/badge/sponsor-GitHub%20Sponsors-ec6cb9)](https://github.com/sponsors/philiprehberger)

Test data generation with builder DSL, traits, and faker utilities.

## Installation

### Gradle (Kotlin DSL)

```kotlin
implementation("com.philiprehberger:test-factory:0.1.3")
```

### Maven

```xml
<dependency>
    <groupId>com.philiprehberger</groupId>
    <artifactId>test-factory</artifactId>
    <version>0.1.3</version>
</dependency>
```

## Usage

```kotlin
import com.philiprehberger.testfactory.*

data class User(val id: String, val name: String, val role: String = "user")

val userFactory = factory<User> {
    default { User(id = Faker.uuid(), name = Faker.name()) }
    trait("admin") { it.copy(role = "admin") }
}

val user = userFactory.build()
val admin = userFactory.build("admin")
val users = userFactory.buildList(10)
```

## API

| Function / Class | Description |
|------------------|-------------|
| `factory<T> { }` | Build a test data factory |
| `Factory.build()` | Build a default instance |
| `Factory.build(override)` | Build with overrides |
| `Factory.build(vararg traits)` | Build with named traits |
| `Factory.buildList(count)` | Build a list of instances |
| `sequence { }` | Auto-incrementing value generator |
| `Faker.name()` / `email()` / `uuid()` | Random test data |
| `Faker.int(range)` / `pick(vararg)` / `boolean()` | Random values |

## Development

```bash
./gradlew test
./gradlew build
```

## License

MIT
