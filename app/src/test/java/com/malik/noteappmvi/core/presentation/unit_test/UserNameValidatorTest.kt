package com.malik.noteappmvi.core.presentation.unit_test

import com.google.common.truth.Truth.assertThat
import com.malik.noteappmvi.core.presentation.tests.unit_test.UserNameValidator
import org.junit.Test



class UserNameValidatorTest{


    private val userNameValidator = UserNameValidator()

    @Test
    fun `validate username with valid UserName returns true`(){

        var isValid =userNameValidator("Malik1")
     //   var isValid =userNameValidator.invoke("Malik1")

        assertThat(isValid).isTrue()
    }


    @Test
    fun `validate username with a empty username returns false` (){

        var isValid = userNameValidator.invoke(" ")
        assertThat(isValid).isFalse()
    }

    @Test
    fun `validate username with an Invalid username returns false` (){

        var isValid = UserNameValidator().invoke("0909")
        assertThat(isValid).isFalse()
    }


    @Test
    fun `validate username with a  space username returns false` (){

        var isValid = userNameValidator.invoke("mal hak1K")
        assertThat(isValid).isFalse()
    }

    @Test
    fun `validate username with already taken usern name  returns false` (){

        var isValid =userNameValidator.invoke("username1")
        assertThat(isValid).isFalse()
    }






}
