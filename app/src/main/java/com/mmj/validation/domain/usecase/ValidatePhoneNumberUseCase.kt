package com.mmj.validation.domain.usecase

import com.mmj.validation.R
import com.mmj.validation.core.functions.isNumber
import com.mmj.validation.core.generic.UiText
import com.mmj.validation.core.generic.usecase.BaseUseCase
import com.mmj.validation.domain.model.ValidationResult


class ValidatePhoneNumberUseCase : BaseUseCase<String, ValidationResult> {

    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePhoneCanNotBeBlank),
            )
        }

        if (!isNumber(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePhoneNumberShouldBeContentJustDigit),
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}