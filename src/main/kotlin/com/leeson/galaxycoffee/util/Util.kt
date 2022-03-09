package com.leeson.galaxycoffee.common.util

fun <R, T> Result<T>.flatMap(transform: (T) -> Result<R>): Result<R> {
    return fold(
        onSuccess = {
            transform(it).fold(
                onSuccess = { r -> Result.success(r) },
                onFailure = { e -> Result.failure(e) }
            )
        },
        onFailure = { Result.failure(it) }
    )
}
