package com.croman.nyzytest.dtos

/**
 * Generic type of object to be returned on an API request
 */
interface Response

/**
 * Generic Response to be used when only the status of the execution
 * of the request is meant to be notified
 */
data class StatusResponse(val status: Int, val code: String, val message: String) : Response
