package com.malik.noteappmvi.core.presentation.tests.unit_test

import android.R.attr.name
import java.util.regex.Matcher
import java.util.regex.Pattern


class UserNameValidator {



    private val users = listOf("username1", "username2", "username3")


    operator fun invoke(userName: String): Boolean {
        //Here check in which case a userName must be not accepted
        //And return false for that

        //A simple RegularExpression Kotlin
        val regex = "^[A-Za-z]\\w{5,29}$"
        val pattern = Pattern.compile(regex)
        pattern.matcher(userName)



        if (userName.isEmpty()) return false

        if (userName.contains(" ")) return false

        if (users.contains(userName)) return false

        if (!pattern.matcher(userName).matches()) return false

       return true
    }
}