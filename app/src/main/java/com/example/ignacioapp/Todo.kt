package com.example.ignacioapp

/**
 * It is not unusual to create classes whose main purpose is to hold data. In such classes,
 * some standard functionality and some utility functions are often mechanically derivable from the data.
 * In Kotlin, these are called data classes and are marked with data
 * */
data class Todo (
    val title: String,
    var isChecked: Boolean = false
)