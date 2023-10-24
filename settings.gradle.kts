enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
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

rootProject.name = "Furtastic"
include(":app")
include(":core")
include(":core_ui")
include(":core_network")
include(":core_datasource")
include(":dog")
include(":dog:dog_data")
include(":dog:dog_domain")
include(":dog:ui_doglist")
include(":dog:ui_dogdetails")
