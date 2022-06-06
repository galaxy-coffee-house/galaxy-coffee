package com.leeson.galaxycoffee.common.util

fun <T, R> Result<T>.flatMap(transform: (T) -> Result<R>): Result<R> {
    return fold(
        onSuccess = {
            transform(it).fold(
                onSuccess = { r -> Result.success(r) },
                onFailure = { e -> Result.failure(e) }
            )
        },
        onFailure = { Result.failure(it) },
    )
}

fun <T> T.toResult(): Result<T> {
    return Result.success(this)
}

fun <T> List<Result<T>>.sequence(): Result<List<T>> {
    return this.fold(emptyList<T>().toResult()) { acc, value ->
        map2(acc, value) { a -> { v -> a + v } }
    }
}

private fun <A, B, C> map2(a: Result<A>, b: Result<B>, f: (A) -> (B) -> C): Result<C> {
    return lift2(f)(a)(b)
}

private fun <A, B, C> lift2(f: (A) -> (B) -> C): (Result<A>) -> (Result<B>) -> Result<C> {
    return { a ->
        { b ->
            a.map(f).flatMap { b.map(it) }
        }
    }
}
