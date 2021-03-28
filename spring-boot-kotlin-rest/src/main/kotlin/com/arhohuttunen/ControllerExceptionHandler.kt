package com.arhohuttunen

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ConstraintViolationProblem {
        val violations = exception.bindingResult.fieldErrors.map {
            ConstraintViolation(it.field, it.defaultMessage)
        }
        return ConstraintViolationProblem(violations)
    }

    data class ConstraintViolationProblem(val violations: List<ConstraintViolation>)

    data class ConstraintViolation(val field: String, val message: String?)
}
