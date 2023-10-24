package com.geetgobindsingh.dog_domain.pagination

interface Paginator<Key, Item> {
    suspend fun loadNextItems(query: String)
    fun reset()
}