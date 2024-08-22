package com.malik.noteappmvi.core.presentation.tests.unit_test

import java.util.regex.Pattern

class PasswordValidator {

    operator fun invoke(
        password: String
    ): Boolean {

        val regex = "^[A-Za-z]\\w{5,29}$"
        val pattern = Pattern.compile(regex)
        pattern.matcher(password)


        if (password.isEmpty()) return false
        if (password.contains(" ")) return false
        if (!pattern.matcher(password).matches()) return false
        if (password.length<8) return false
        if (!password.any { it.isUpperCase() }) return false
        if (!password.any { it.isDigit() }) return false

        return true


    }
}