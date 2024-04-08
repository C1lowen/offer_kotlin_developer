package com.example.tztest

object ObjectStorage {
    private val items: MutableList<Item> = mutableListOf()

    fun addItem(item: Item) {
        items.add(item)
    }

    fun removeItem(item: Item) {
        items.remove(item)
    }

    fun getAllItems(): List<Item> {
        return items.toList()
    }
}