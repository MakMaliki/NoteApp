package com.malik.noteappmvi.core.presentation.tests.unit_test

class ConfirmPasswordValidator {

    operator fun invoke(password: String, confirmPass: String): Boolean {

        return password == confirmPass

    }
}