package com.geetgobindsingh.core_network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders

class KtorClient : Api {
    companion object {
        private const val TIME_OUT = 60_000
    }

    override fun getClient(): HttpClient {
        return HttpClient(Android) {

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })

                engine {
                    connectTimeout = TIME_OUT
                    socketTimeout = TIME_OUT
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Logger Ktor => $message")
                    }

                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    println("HTTP status: ${response.status.value}")
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("x-api-key", "live_3QAR2Dnyhd6Q42lCk5q0FQFEzZLN1WhdO6FDQebtpgW4mNJBKGYA6yk2lw3cC0sw")
            }
        }
    }
}