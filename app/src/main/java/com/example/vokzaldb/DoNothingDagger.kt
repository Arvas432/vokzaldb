package com.example.vokzaldb

import dagger.Component

@Component
interface DoNothingDagger {
    fun nothing(): DoNothingClass
}