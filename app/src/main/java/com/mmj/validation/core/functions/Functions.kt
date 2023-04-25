package com.mmj.validation.core.functions

import android.util.Patterns

// this fun use for check if value is number
fun isNumber(value: String): Boolean {
    return value.isEmpty() || Regex("^\\d+\$").matches(value)
}

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isPasswordValid(password: String): Boolean {
    return password.any { it.isDigit() } &&
            password.any { it.isLetter() }
}