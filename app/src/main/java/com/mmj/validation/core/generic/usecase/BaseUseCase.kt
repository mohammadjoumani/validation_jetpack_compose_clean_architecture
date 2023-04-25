package com.mmj.validation.core.generic.usecase

interface BaseUseCase<In, Out> {
    fun execute(input: In): Out
}