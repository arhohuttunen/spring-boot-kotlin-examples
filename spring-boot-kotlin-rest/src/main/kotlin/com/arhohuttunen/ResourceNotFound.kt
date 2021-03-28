package com.arhohuttunen

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
open class ResourceNotFound(message: String) : RuntimeException(message)
