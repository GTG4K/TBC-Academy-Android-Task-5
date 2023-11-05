package com.example.tbcacademyandroidtask5.Util

class Validate {
    private val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    var validationPassed: Boolean = true;

    fun valueFilled(input: String): Boolean {
        if (input.trim().isEmpty()) {
            validationPassed = false
            return false
        }
        return true
    }

    fun isEmail(input: String): Boolean {
        if (!input.matches(emailRegex)) {
            validationPassed = false
            return false
        }
        return true
    }
}