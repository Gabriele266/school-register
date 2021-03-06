package com.school.schoolregister.controllers

import java.lang.Math.abs
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.ThreadLocalRandom

/**
 * Generates a random string of given length
 */
fun generateRandomString(length: Int = 10): String {
    var builder = StringBuilder()

    for (a in 0..length)
        builder.append(ThreadLocalRandom.current().nextInt())

    return builder.toString()
}

/**
 * Generates a randomic integer between min and max
 */
fun generateRandomInt(min: Int = 0, max: Int = 200): Int =
    kotlin.math.abs(ThreadLocalRandom.current().nextInt() % (max - min) + min)

/**
 * Generates a randomic integer between min and max
 */
fun generateRandomLong(min: Long = 0, max: Long = 200): Long =
    kotlin.math.abs(ThreadLocalRandom.current().nextLong() % (max - min) + min)

/**
 * Generates a random sequence
 */
fun <T> generateSequence(count: Int, generator: (index: Int) -> T): Sequence<T> {
    var list = mutableListOf<T>()

    for (i in 0..count)
        list.add(generator(i))

    return list.asSequence()
}

fun currentDateTimeMillis(): Long =
    LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)