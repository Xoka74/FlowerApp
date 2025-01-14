package com.shurdev.domain.models.trade

data class PlantTrade(
    val name: String,
    val imageData: ByteArray?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlantTrade

        if (name != other.name) return false
        if (!imageData.contentEquals(other.imageData)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + imageData.contentHashCode()
        return result
    }
}