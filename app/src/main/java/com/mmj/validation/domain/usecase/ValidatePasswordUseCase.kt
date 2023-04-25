package com.mmj.validation.domain.usecase

import com.mmj.validation.R
import com.mmj.validation.core.functions.isPasswordValid
import com.mmj.validation.core.generic.UiText
import com.mmj.validation.core.generic.usecase.BaseUseCase
import com.mmj.validation.domain.model.ValidationResult

class ValidatePasswordUseCase : BaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePasswordNeedsToConsistOfAtLeastEightCharacters),
            )
        }

        if (!isPasswordValid(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePasswordNeedsToContainAtLeastOneLetterAndDigit),
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}