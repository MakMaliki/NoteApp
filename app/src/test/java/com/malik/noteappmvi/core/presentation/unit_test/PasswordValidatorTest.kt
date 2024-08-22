package com.malik.noteappmvi.core.presentation.unit_test

import com.google.common.truth.Truth.assertThat
import com.malik.noteappmvi.core.presentation.tests.unit_test.PasswordValidator
import org.junit.Test


class PasswordValidatorTest {

    private val passwordValidator = PasswordValidator()

    @Test
    fun `validate password  with a valid Password returns true`() {
        val isValid = passwordValidator.invoke("Jamal1234")
        assertThat(isValid).isTrue()
    }

    @Test
    fun `validate password with a Empty Password returns false`(){
        val isValid = passwordValidator.invoke(password = "")

        assertThat(isValid).isFalse()
    }
    @Test
    fun `validate password, password contains a space returns false`(){
        val isValid = passwordValidator.invoke(password = " ")
        assertThat(isValid).isFalse()
    }

    @Test
    fun `validate password, password does not contains a upperCase returns false`(){
        val isValid = passwordValidator.invoke(password = " pass12345")
        assertThat(isValid).isFalse()
    }

    @Test
    fun `validate password, password does not contains a digig returns false`(){
        val isValid = passwordValidator.invoke(password = "passss")
        assertThat(isValid).isFalse()
    }



}