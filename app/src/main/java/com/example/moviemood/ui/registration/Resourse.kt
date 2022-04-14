package com.example.moviemood.ui.registration

data class Resource(val status: Status) {

    companion object {
        fun  success(): Resource = Resource(status = Status.SUCCESS)

        fun  error(): Resource =
            Resource(status = Status.ERROR)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    ERROR_VALIDATION_EMAIL,
    ERROR_VALIDATION_PASSWORD,
    ERROR_VALIDATION_PASSWORD_REPEAT
}