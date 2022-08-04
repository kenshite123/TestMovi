package com.example.testmovi.data.cache

import com.google.gson.Gson

class CacheServiceImpl(
    private val fileService: FileService,
    private val gson: Gson
) : CacheService {
    companion object {
        private const val SUFFIX = ".c9"
    }
}