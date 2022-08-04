package com.example.testmovi.data.cache

interface FileService {
    fun saveData(fileName: String, data: String)

    fun getData(fileName: String): String?
}