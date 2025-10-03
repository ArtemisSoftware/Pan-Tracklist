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

rootProject.name = "PanTracklist"
include(":app")
include(":core:database")
include(":core:network")
include(":core:domain")
include(":features:albums:data")
include(":features:albums:domain")
include(":core:designsystem")
include(":core:presentation")
include(":features:albums:presentation")
