# test-factory

[![Tests](https://github.com/philiprehberger/kt-test-factory/actions/workflows/publish.yml/badge.svg)](https://github.com/philiprehberger/kt-test-factory/actions/workflows/publish.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.philiprehberger/test-factory.svg)](https://central.sonatype.com/artifact/com.philiprehberger/test-factory)
[![Last updated](https://img.shields.io/github/last-commit/philiprehberger/kt-test-factory)](https://github.com/philiprehberger/kt-test-factory/commits/main)

Test data generation with builder DSL, traits, and faker utilities.

## Installation

### Gradle (Kotlin DSL)

```kotlin
implementation("com.philiprehberger:test-factory:0.2.0")
```

### Maven

```xml
<dependency>
    <groupId>com.philiprehberger</groupId>
    <artifactId>test-factory</artifactId>
    <version>0.2.0</version>
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
| `Faker.timestamp(from, to)` | Random `Instant` in range |
| `Faker.date(from, to)` | Random `LocalDate` in range |

## Development

```bash
./gradlew test
./gradlew build
```

## Support

If you find this project useful:

⭐ [Star the repo](https://github.com/philiprehberger/kt-test-factory)

🐛 [Report issues](https://github.com/philiprehberger/kt-test-factory/issues?q=is%3Aissue+is%3Aopen+label%3Abug)

💡 [Suggest features](https://github.com/philiprehberger/kt-test-factory/issues?q=is%3Aissue+is%3Aopen+label%3Aenhancement)

❤️ [Sponsor development](https://github.com/sponsors/philiprehberger)

🌐 [All Open Source Projects](https://philiprehberger.com/open-source-packages)

💻 [GitHub Profile](https://github.com/philiprehberger)

🔗 [LinkedIn Profile](https://www.linkedin.com/in/philiprehberger)

## License

[MIT](LICENSE)
