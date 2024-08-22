package com.malik.noteappmvi.core.presentation.unit_test

import com.google.common.truth.Truth.assertThat
import com.malik.noteappmvi.core.presentation.tests.unit_test.ConfirmPasswordValidator
import org.junit.Test


class ConfirmPasswordValidatorTest{

    @Test
    fun `password and confirm same returns true`(){
        val result = ConfirmPasswordValidator().invoke(
            password = "Malik123",
            confirmPass = "Malik123",
        )
        assertThat(result).isTrue()
    }


    @Test
    fun `password and confirm are not the same returns False`(){

        val result = ConfirmPasswordValidator().invoke(
            password = "Malik123",
            confirmPass = "Malik12333",
        )
        assertThat(result).isFalse()
    }
}