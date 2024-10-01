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
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RetoTecnicoApp"
include(":app")
include(":core")
include(":login")
include(":home")
include(":core:navigation")
include(":core:networking")
include(":core:common")
include(":core:designsystem")
include(":login:presentation")
include(":login:domain")
include(":login:infrastructure")
include(":core:domain")
include(":home:presentation")
include(":home:infrastructure")
include(":home:domain")
