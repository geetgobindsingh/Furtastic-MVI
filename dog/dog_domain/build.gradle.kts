apply {
    from("$rootDir/library-build.gradle")
}

dependencies {
    "implementation"(projects.core)
    "implementation"(libs.coroutines.core)
    "implementation"(libs.coroutines.stdlib)
}