<h1 align="center">Simple Android App</h1>

<p align="center">  
This project demonstrates modern Android development with Jetpack Compose, Hilt, Coroutines, Flow, Jetpack (Room, ViewModel), and Material 3 based on Clean architecture.
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 24.
- [Kotlin](https://kotlinlang.org/) based, utilizing [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations.
- Jetpack Libraries:
  - Jetpack Compose: Android’s modern toolkit for declarative UI development.
  - Lifecycle: Observes Android lifecycles and manages UI states upon lifecycle changes.
  - ViewModel: Manages UI-related data and is lifecycle-aware, ensuring data survival through configuration changes.
  - Room: Constructs a database with an SQLite abstraction layer for seamless database access.
  - [Hilt](https://dagger.dev/hilt/): Facilitates dependency injection.
- Architecture:
  - MVVM Architecture (View - ViewModel - Model): Facilitates separation of concerns and promotes maintainability.
  - Repository Pattern: Acts as a mediator between different data sources and the application's business logic.
- [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization): Kotlin multiplatform / multi-format reflectionless serialization.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API for code generation and analysis.
- [Coil](https://coil-kt.github.io/coil/) Jetpack compose image loading library.
- [Constraint Layout] For complex ui designs.

## Architecture
**Simple Android App** adheres to the Clean MVVM architecture and implements the Repository pattern, aligning with [Google's official architecture guidance](https://developer.android.com/topic/architecture).
- Why follow Clean MVVVM architecture and Repository Pattern ? Because it will help you to build more scalable, testable and clean project structure in your application.

## Challenges
- Small time frame to build this application
  - Why? Because you need to analyze the project first, come up with the plan how you will create the application in scalable and testable way. Also Project configuration takes a lot of time.
- Cache Mechanism
  - Why? You need to convert pojo to entity which tedious to do within small time frame
- PDF Images
  - Why? It's unusual to see using pdf for image which needed custom decoder convert to bitmap so that we can show those images
