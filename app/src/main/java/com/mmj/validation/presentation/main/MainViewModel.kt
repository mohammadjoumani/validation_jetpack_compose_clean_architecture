package com.mmj.validation.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mmj.validation.core.generic.UiText
import com.mmj.validation.domain.usecase.ValidateEmailUseCase
import com.mmj.validation.domain.usecase.ValidatePasswordUseCase

class MainViewModel : ViewModel() {

    private val validateEmailUseCase = ValidateEmailUseCase()
    private val validatePasswordUseCase = ValidatePasswordUseCase()

    var formState by mutableStateOf(MainState())

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
                validateEmail()
            }

            is MainEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
                validatePassword()
            }

            is MainEvent.VisiblePassword -> {
                formState = formState.copy(isVisiblePassword = event.isVisiblePassword)
            }

            is MainEvent.Submit -> {
                if (validateEmail() && validatePassword()) {
//                    login()
                }
            }
        }
    }

    private fun validateEmail(): Boolean {
        val emailResult = validateEmailUseCase.execute(formState.email)
        formState = formState.copy(emailError = emailResult.errorMessage)
        return emailResult.successful
    }

    private fun validatePassword(): Boolean {
        val passwordResult = validatePasswordUseCase.execute(formState.password)
        formState = formState.copy(passwordError = passwordResult.errorMessage)
        return passwordResult.successful
    }

}

sealed class MainEvent {
    data class EmailChanged(val email: String) : MainEvent()
    data class PasswordChanged(val password: String) : MainEvent()
    data class VisiblePassword(val isVisiblePassword: Boolean) : MainEvent()
    object Submit : MainEvent()
}

data class MainState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val isVisiblePassword: Boolean = false
)