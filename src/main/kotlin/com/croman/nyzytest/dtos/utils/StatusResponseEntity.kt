package com.croman.nyzytest.dtos.utils

import com.croman.nyzytest.dtos.StatusResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class StatusResponseEntity {
    companion object {
        fun notFound(message: String) =
            ResponseEntity<StatusResponse>(StatusResponse(404, "not_found", message), HttpStatus.NOT_FOUND)

        fun badRequest(message: String) =
            ResponseEntity<StatusResponse>(StatusResponse(400, "bad_request", message), HttpStatus.BAD_REQUEST)

        fun okRequest(message: String) =
            ResponseEntity<StatusResponse>(StatusResponse(200, "successful_request", message), HttpStatus.OK)
    }
}