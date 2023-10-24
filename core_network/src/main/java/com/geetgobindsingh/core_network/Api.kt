package com.geetgobindsingh.core_network

interface Api {
    fun getClient(): io.ktor.client.HttpClient
}