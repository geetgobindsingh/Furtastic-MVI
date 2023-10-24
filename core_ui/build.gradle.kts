apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies {
    "api"(libs.core.ktx)
    "api"(libs.activity.compose)
    "api"(platform(libs.compose.bom))
    "api"(libs.ui)
    "api"(libs.navigation.compose)
    "api"(libs.ui.graphics)
    "api"(libs.ui.tooling.preview)
    "api"(libs.material3)
    "api"(libs.coil)
    "testApi"(libs.junit)
    "androidTestApi"(platform(libs.compose.bom))
    "androidTestApi"(libs.ui.test.junit4)
    "debugApi"(libs.ui.tooling)
    "debugApi"(libs.ui.test.manifest)

}