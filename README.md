# 🗞️ Khalid NYT — Most Popular Articles (Compose + MVVM)

A modular Android app that fetches and displays the **New York Times – Most Popular (Most Viewed)** articles. Built with **Jetpack Compose**, **MVVM**, **Kotlin Coroutines/Flow**, and **Hilt**.

> Endpoint: `/svc/mostpopular/v2/viewed/{period}.json` where `{period}` ∈ **1 / 7 / 30**.

---

## ✨ Highlights

- 🎯 **Clean architecture** with unidirectional data flow (UI → VM → UseCase → Repo → Network/DB → UI).
- 🧩 **Strict modularization** following Google’s guide: <https://developer.android.com/topic/modularization>.
- ⚙️ **Hilt DI**, **Coroutines/Flow**, **Room**, **Retrofit/OkHttp**, **kotlinx-serialization**, **Coil 3**.
- 🧪 **Unit tests** for repository and flows with `kotlinx-coroutines-test` & `MockK`.

---

## 🧱 Module Graph

```

:app
:core:common
:core:domain
:core:network
:core:ui
:core:analytics
:core:database
:core:datastore
:domain:articles
:data:nyt
:feature:articles

```

**Why this layout?**  
- **Isolation** of concerns and faster builds.  
- **Reuse** of core modules across features.  
- **Scalability** as more features are added.

---

## 🏗️ Architecture

```

Compose UI (feature)
│ state/intents
▼
ViewModel (StateFlow)
│
UseCases (domain)
│
Repository (domain → data)
├── Network (Retrofit + kotlinx-serialization)
└── Database (Room)

```

- **State** exposed as immutable `StateFlow`.
- **Mappers** isolate DTO/Entity from Domain.
- **Safe flows** wrap errors (no crashes on network failures).

---

## 🔧 Setup & Run

### Prerequisites
- Android Studio **Koala** (or newer)
- JDK **11+**
- `compileSdk = 36`, `minSdk = 24`

### 1) Add your NYT API key
Put it in one of the following (don’t commit secrets):

**`local.properties`**
```

NYT_API_KEY=your_key_here

```

_or_

**`gradle.properties`**
```

NYT_API_KEY=your_key_here

```

### 2) Build & Run
- Sync Gradle → run the **app** configuration on a device/emulator.

---

## 🧪 Testing

- Unit tests for repository, mapping, and flow handling.
- Uses `kotlinx-coroutines-test` + `MockK`.

Run:
```

./gradlew test

```

---

## 🖼️ UI/UX

- Built in **Jetpack Compose**.
- **Coil 3** for image loading.
- (Planned) transitions & image fade-in.

---

## 🛡️ Security

- API key is read from Gradle/local properties at build time.
- Never commit secrets. `.gitignore` excludes local IDE & secrets.

---

## 🗺️ Future Improvements / Roadmap

1. **Implementing Composition provider for Dimensions** instead of fixed values.  
2. **Implementing Article Details screen.**  
3. **Implementing Composition provider for custom color scheme.**  
4. **Implementing `ErrorReporter` class for Crashlytics.**  
5. **Move all hard-coded strings to constants and localize** (multi-language).  
6. **Implement DataStore** to save latest user-selected **period** and basic user details.  
7. **Add animations** (screen transitions & image fade-in when loaded).  
8. **Pull-to-refresh** on the Articles list.  
9. **Aim for 100% unit test coverage** across modules.  
10. **Add live/mocked test** flavors for deterministic demos.  
11. **Add PR workflow** to enforce unit-test coverage & checks in CI.

---

## 📄 License

This repository is provided for demonstration purposes.
