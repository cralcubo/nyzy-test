package com.croman.nyzytest.exceptions.users

/**
 * Throw this exception when something is off or missing
 * when dealing with a user
 */
class IllegalUserException(message: String) : Exception(message)