package com.malik.noteappmvi.core.presentation.tests.instrumented_unit_test

import android.content.Context
import android.util.Patterns

class EmailFormatValidator (private val context: Context) {

    operator fun invoke(email:String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}