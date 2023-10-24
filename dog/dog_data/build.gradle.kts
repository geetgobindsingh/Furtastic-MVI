apply {
    from("$rootDir/library-build.gradle")
}

apply(plugin = "kotlinx-serialization")

dependencies {
    "implementation"(projects.core)
    "implementation"(projects.coreDatasource)
    "implementation"(projects.coreNetwork)
    "implementation"(projects.dog.dogDomain)
}