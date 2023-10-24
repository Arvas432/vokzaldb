package com.example.vokzaldb

import javax.inject.Inject

class DoNothingClass @Inject constructor(){
    fun getNothing(): Int{
        return 0
    }
}