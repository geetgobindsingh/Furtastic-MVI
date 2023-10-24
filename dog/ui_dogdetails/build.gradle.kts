apply {
    from("$rootDir/android-library-build.gradle")
}

//apply(plugin = "kotlinx-serialization")

dependencies {
    "implementation"(projects.core)
    "implementation"(projects.coreUi)
    "implementation"(projects.dog.dogData)
    "implementation"(projects.dog.dogDomain)
}