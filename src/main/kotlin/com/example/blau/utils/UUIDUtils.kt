package com.example.blau.utils

import java.math.BigInteger
import java.nio.ByteBuffer
import java.util.UUID

// simulate postgres uuid comparison
fun UUID.toBigInteger(): BigInteger {
    val buffer = ByteBuffer.allocate(16)
    buffer.putLong(this.mostSignificantBits)
    buffer.putLong(this.leastSignificantBits)
    return BigInteger(1, buffer.array())
}

fun orderUUIDs(uuid1: UUID, uuid2: UUID): Pair<UUID, UUID> {
    return if (uuid1.toBigInteger() <= uuid2.toBigInteger()) {
        uuid1 to uuid2
    } else {
        uuid2 to uuid1
    }
}
