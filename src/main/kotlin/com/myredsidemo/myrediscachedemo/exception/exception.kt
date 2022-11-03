package com.myredsidemo.myrediscachedemo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class InvoiceNotFoundException(message: String?) : RuntimeException(message) {
    companion object {
        private const val serialVersionUID = 7428051251365675318L
    }
}