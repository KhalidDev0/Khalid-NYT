pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "Khalid-NYT"
include(":app")
include(":feature:articles")
include(":domain:articles")
include(":data:nyt")
include(":core:network")
include(":core:database")
include(":core:datastore")
include(":core:ui")
include(":core:analytics")
include(":core:common")
include(":core:domain")

project(":feature:articles").projectDir = file("feature/articles")
project(":domain:articles").projectDir = file("domain/articles")
project(":data:nyt").projectDir = file("data/nyt")
project(":core:network").projectDir = file("core/network")
project(":core:database").projectDir = file("core/database")
project(":core:datastore").projectDir = file("core/datastore")
project(":core:ui").projectDir = file("core/ui")
project(":core:analytics").projectDir = file("core/analytics")
project(":core:common").projectDir = file("core/common")
project(":core:domain").projectDir = file("core/domain")
