package com.kikepb7.rickandmortyapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform