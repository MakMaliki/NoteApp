package com.malik.noteappmvi.core.presentation.instrumented_unit_test

import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.malik.noteappmvi.core.presentation.tests.instrumented_unit_test.EmailFormatValidator
import org.junit.After
import org.junit.Before
import org.junit.Test


class EmailFormatValidatorTestTest{

    private lateinit var emailFormatValidator: EmailFormatValidator


    //Accesss Object for Example Database
    //Initialize
    @Before
    fun setUp(){

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        emailFormatValidator = EmailFormatValidator(context)

    }


    //Close it here
    @After
    fun tearDown(){

      //  emailFormatValidator = null

    }


    @Test
    fun validateEmailWithValidEmailReturnsTrue(){
        val isValid = emailFormatValidator.invoke("email11@gmail.com")

        assertThat(isValid).isTrue()

    }

}