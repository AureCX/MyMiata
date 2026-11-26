package com.example.mymiata

/**
 * Encodes a 32-bit NEC infrared code (as printed by common IR receivers, e.g. 0x10EFD827)
 * into the alternating on/off pattern, in microseconds, expected by
 * [android.hardware.ConsumerIrManager.transmit].
 */
object NecIrEncoder {
    const val CARRIER_FREQUENCY_HZ = 38_000

    private const val LEADER_MARK = 9_000
    private const val LEADER_SPACE = 4_500
    private const val BIT_MARK = 560
    private const val ZERO_SPACE = 560
    private const val ONE_SPACE = 1_690

    fun encode(code: Long): IntArray {
        require(code in 0..0xFFFF_FFFFL) { "NEC codes are 32 bits" }
        val pattern = ArrayList<Int>(2 + 32 * 2 + 1)
        pattern += LEADER_MARK
        pattern += LEADER_SPACE
        for (bit in 31 downTo 0) {
            pattern += BIT_MARK
            pattern += if ((code shr bit) and 1L == 1L) ONE_SPACE else ZERO_SPACE
        }
        pattern += BIT_MARK // trailing mark terminates the last bit
        return pattern.toIntArray()
    }
}
