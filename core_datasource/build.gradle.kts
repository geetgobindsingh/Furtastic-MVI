apply {
    from("$rootDir/library-build.gradle")
}

apply(plugin = "kotlinx-serialization")

plugins {
    id("app.cash.sqldelight")
}

dependencies {
    "api"(libs.sqldelight.runtime)
    "api"(libs.sqldelight.primitive.adapters)
    "api"(libs.sqldelight.coroutines.extensions)
    "api"(libs.kotlinx.serialization.json)
}

sqldelight {
    databases {
        create("FurtasticDatabase") {
            packageName.set("com.geetgobindsingh.core_datasource")
            srcDirs.setFrom("src/main/sqldelight")
        }
    }
}
