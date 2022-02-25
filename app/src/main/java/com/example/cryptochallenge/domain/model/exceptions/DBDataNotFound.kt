package com.example.cryptochallenge.domain.model.exceptions


import java.lang.Exception

class DBDataNotFound(message: String = "Could not find data locally"): Throwable(message) {
}