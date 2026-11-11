package com.example.mymiata

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class NecIrEncoderTest {

    @Test
    fun patternHasLeaderThirtyTwoBitsAndTrailingMark() {
        val pattern = NecIrEncoder.encode(0x10EFD827L)
        assertEquals(2 + 32 * 2 + 1, pattern.size)
        assertEquals(9_000, pattern[0])
        assertEquals(4_500, pattern[1])
        assertEquals(560, pattern.last())
    }

    @Test
    fun bitsAreSentMostSignificantFirst() {
        val pattern = NecIrEncoder.encode(0x10EFD827L)
        val spaces = (0 until 32).map { pattern[3 + it * 2] }
        val decoded = spaces.fold(0L) { acc, space -> (acc shl 1) or if (space == 1_690) 1L else 0L }
        assertEquals(0x10EFD827L, decoded)
    }

    @Test
    fun allZeroCodeUsesShortSpaces() {
        val pattern = NecIrEncoder.encode(0L)
        assertArrayEquals(IntArray(64) { 560 }, pattern.copyOfRange(2, 66))
    }

    @Test(expected = IllegalArgumentException::class)
    fun rejectsCodesWiderThan32Bits() {
        NecIrEncoder.encode(0x1_0000_0000L)
    }
}
