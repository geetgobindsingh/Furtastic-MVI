apply {
    from("$rootDir/library-build.gradle")
}
apply(plugin = "kotlinx-serialization")

dependencies {
    "api"(libs.bundles.networking)
}