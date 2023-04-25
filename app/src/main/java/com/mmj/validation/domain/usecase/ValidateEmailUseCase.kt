package com.mmj.validation.domain.usecase

import com.mmj.validation.R
import com.mmj.validation.core.functions.isEmailValid
import com.mmj.validation.core.generic.UiText
import com.mmj.validation.core.generic.usecase.BaseUseCase
import com.mmj.validation.domain.model.ValidationResult

class ValidateEmailUseCase: BaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strTheEmailCanNotBeBlank)
            )
        }
        if (!isEmailValid(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThatsNotAValidEmail)
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}