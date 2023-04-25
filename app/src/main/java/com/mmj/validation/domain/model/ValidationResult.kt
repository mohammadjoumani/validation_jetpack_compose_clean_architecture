package com.mmj.validation.domain.model

import com.mmj.validation.core.generic.UiText


data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)

